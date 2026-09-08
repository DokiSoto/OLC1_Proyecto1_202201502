/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
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
/**
 *
 * @author dxssm
 */
public class CompilerService {
    public Result execute (String source){
           List<TokenInfo> tokens = new ArrayList<>();
           List<CompileError> errors = new ArrayList<>();
           Lexer lexicalLexer = new Lexer(new StringReader(source));
           
           try{
              Symbol current;
              
              do{
                 current = lexicalLexer.next_token();
              
              }
              while(current.sym != sym.EOF);
           
           }catch(LexicalException ex){
                
           }catch(Exception ex){
               errors.add(new CompileError("LEXCICO", ex.getMessage(), 0, 0));
           }
           
           tokens.addAll(lexicalLexer.getTokens());
           errors.addAll(lexicalLexer.getErrors());
           
           if(!errors.isEmpty()){
               return new Result(tokens, errors, "Ocurrio eroor en algo");
               
           }
           
           Parser parser = null;
           
           try{
              Lexer parserLexer = new Lexer(new StringReader(source));
              parser = new Parser(parserLexer);
              
              parser.parse();
           } catch (ParseAbortException ex){
               errors.add(new CompileError("sintactico", ex.getMessage() == null? "Error durante analisis": ex.getMessage(), 0,0));
           }
    }
    
}
