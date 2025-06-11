package com.auberer.compilerdesignlectureproject.e2e;

import com.auberer.compilerdesignlectureproject.CompilerDesignLectureProject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class EndToEndTest {

  private static final File TEST_ROOT = new File("src/test-e2e");

  @BeforeAll
  static void setup() {
    if (!TEST_ROOT.exists() || !TEST_ROOT.isDirectory()) {
      throw new IllegalStateException("test-e2e directory not found.");
    }
  }

  static Stream<Arguments> testCasesProvider() {
    return Arrays.stream(Objects.requireNonNull(TEST_ROOT.listFiles(File::isDirectory)))
        .map(File::getAbsolutePath)
        .map(Arguments::of);
  }

  @ParameterizedTest
  @MethodSource("testCasesProvider")
  void runE2ETest(String testDirPath) {
    File testDir = new File(testDirPath);
    File argsFile = new File(testDir, "args.txt");
    File expectedOutputFile = new File(testDir, "out.txt");

    // Check if test should be executed
    File disabledFile = new File(testDir, "disabled");
    if (disabledFile.exists())
      return;

    assertTrue(argsFile.exists(), "Missing args.txt in " + testDir.getName());
    assertTrue(expectedOutputFile.exists(), "Missing out.txt in " + testDir.getName());

    try {
      String argsString = "-c " + Files.readString(argsFile.toPath());
      String expectedOutput = Files.readString(expectedOutputFile.toPath())
          .trim()
          .replaceAll("\r", "");

      List<String> command = new ArrayList<>();
      command.add(System.getProperty("java.home") + "/bin/java");
      command.add("-ea");
      command.add("-cp");
      command.add(System.getProperty("java.class.path"));
      command.add(CompilerDesignLectureProject.class.getCanonicalName());
      command.addAll(List.of(argsString.split(" ")));

      ProcessBuilder pb = new ProcessBuilder(command);
      pb.redirectErrorStream(true);
      Process process = pb.start();

      String actualOutput = new String(process.getInputStream().readAllBytes())
          .trim()
          .replaceAll("\r", "");
      process.waitFor();

      assertEquals(expectedOutput, actualOutput, "Mismatch in " + testDir.getName());
    } catch (IOException | InterruptedException e) {
      fail("Error running test in " + testDir.getName() + ": " + e.getMessage());
    }
  }
}
