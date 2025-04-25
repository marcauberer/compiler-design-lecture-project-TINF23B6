package com.auberer.compilerdesignlectureproject;

import com.auberer.compilerdesignlectureproject.ast.ASTEntryNode;
import com.auberer.compilerdesignlectureproject.ast.ASTVisualizer;
import com.auberer.compilerdesignlectureproject.lexer.Lexer;
import com.auberer.compilerdesignlectureproject.parser.Parser;
import com.auberer.compilerdesignlectureproject.reader.Reader;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CompilerDesignLectureProject {
  private static final Logger log = LoggerFactory.getLogger(CompilerDesignLectureProject.class);

  public static void main(String[] args) {
    Options cliOptions = new Options()
        .addOption("h", "help", false, "Print this help text")
        .addOption("tokens", "dump-tokens", false, "Dump the lexed tokens")
        .addOption("ast", "dump-ast", false, "Dump the AST as dot file");

    DefaultParser cliParser = new DefaultParser();
    try {
      CommandLine cli = cliParser.parse(cliOptions, args);
      if (cli.hasOption('h')) {
        new HelpFormatter().printHelp("tinf-compiler args...", cliOptions);
        System.exit(0);
      }

      if (args.length == 0) {
        System.out.println("No file path provided!");
        System.exit(1);
      }
      // Convert the first command line argument to a Path
      String[] positionalArgs = cli.getArgs();
      Path path = Paths.get(positionalArgs[0]).toAbsolutePath();

      // Read, lex and parse the input file
      ASTEntryNode ast;
      if (cli.hasOption("antlr")) {
        System.out.println("Compiling with ANTLR parser...");
        ast = parseWithANTLRParser(path);
      } else {
        System.out.println("Compiling with own parser...");

        boolean dumpTokens = cli.hasOption("tokens");
        ast = parseWithOwnParser(path, dumpTokens);
      }
      assert ast != null;

      // Dump AST
      if (cli.hasOption("ast")) {
        System.out.println("Dumping AST ...");
        ASTVisualizer visualizer = new ASTVisualizer();
        String dot = visualizer.visitEntry(ast);
        System.out.println(dot);
      }

      // ToDo(Marc): Implement
    } catch (ParseException e) {
      new HelpFormatter().printHelp("tinf-compiler args...", cliOptions);
    } catch (Exception e) {
      log.error("An error occurred", e);
    }
  }

  static ASTEntryNode parseWithOwnParser(Path path, boolean dumpTokens) {
    // Create a new Reader object with the given file path
    Reader reader = new Reader(path);

    // Create lexer and parser
    Lexer lexer = new Lexer(reader, dumpTokens);
    Parser parser = new Parser(lexer);

    // Parse the input file
    return parser.parse();
  }

  static ASTEntryNode parseWithANTLRParser(Path path) throws IOException {
    return null;
  }
}