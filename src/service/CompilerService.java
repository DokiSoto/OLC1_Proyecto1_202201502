/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
/**
 *
 * @author dxssm
 */
import Analizadores.CompileError;
import Analizadores.Lexer;

import Analizadores.LexicalException;
import Analizadores.ParseAbortException;
import Analizadores.Parser;
import Analizadores.TokenInfo;
import Analizadores.sym;
import engine.BattleEngine;
import engine.BattleScriptExecutionException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.Symbol;
import Modelo.ProgramaMOdelo;

public class CompilerService {

    public Result execute(String source) {

        List<TokenInfo> tokens = new ArrayList<>();
        List<CompileError> errors = new ArrayList<>();

        Lexer lexicalLexer = new Lexer(new StringReader(source));

        try {

            Symbol current;

            do {
                current = lexicalLexer.next_token();
            } while (current.sym != sym.EOF);

        } catch (LexicalException ex) {

        } catch (Exception ex) {

            errors.add(new CompileError("Léxico",ex.getMessage(),0,0));
        }

        tokens.addAll(lexicalLexer.getTokens());
        errors.addAll(lexicalLexer.getErrors());

        if (!errors.isEmpty()) {

            return new Result(tokens,errors,"ocurrio un error en algo");
        }

        Parser parser = null;

        try {

            Lexer parserLexer = new Lexer(
                    new StringReader(source)
            );

            parser = new Parser(parserLexer);

            parser.parse();

        } catch (ParseAbortException ex) {

        } catch (Exception ex) {

            errors.add(new CompileError("Sintáctico",ex.getMessage() == null? "Error en algo" : ex.getMessage(),0,0));
        }

        if (parser != null) {
            errors.addAll(parser.getErrors());
        }

        if (!errors.isEmpty()) {

            return new Result(tokens,errors,"Error humano");
        }

        ProgramaMOdelo program = parser.getProgram();

        if (program == null) {

            errors.add(new CompileError("Sintáctico","ERROR EN ALGO",0,0));

            return new Result(tokens,errors,"ERROR EN ALGO");
        }

        try {

            BattleEngine engine = new BattleEngine();

            String output = engine.execute(program);

            return new Result(tokens,errors,"ANÁLISIS LÉXICO CORRECTO\n"+ "ANÁLISIS SINTÁCTICO CORRECTO\n"+ "EJECUCIÓN CORRECTA\n\n"+ output);

        } catch (BattleScriptExecutionException ex) {

            errors.add(new CompileError("Ejecución",ex.getMessage(),0,0));

            return new Result(tokens,errors,"Ejecución detenida:\n" + ex.getMessage());

        } catch (RuntimeException ex) {
//MAJEEEEEE
            errors.add(new CompileError("Ejecución",ex.getMessage(),0,0));

            return new Result(tokens,errors,"Error en algo" + ex.getMessage());
        }
    }

    public static class Result {//maje

        private final List<TokenInfo> tokens;
        private final List<CompileError> errors;
        private final String output;

        public Result(List<TokenInfo> tokens,List<CompileError> errors,String output
        ) {
            this.tokens = tokens;
            this.errors = errors;
            this.output = output;
        }

        public List<TokenInfo> getTokens() {
            return tokens;
        }

        public List<CompileError> getErrors() {
            return errors;
        }

        public String getOutput() {
            return output;
        }

        public boolean isSuccess() {
            return errors.isEmpty();
        }
    }
}