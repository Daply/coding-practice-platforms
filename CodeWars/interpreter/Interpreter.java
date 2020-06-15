
import java.util.*;
import java.util.regex.*;

// Kata: Simple Interactive Interpreter (1 kyu)

public class Interpreter {
    
//    Task
//    You will create an interpreter which takes inputs 
//    described below and produces outputs, 
//    storing state in between each input.
//
//    If you're not sure where to start with this kata, 
//    check out my Simpler Interactive Interpreter kata, 
//    which greatly simplifies the interpreter by removing functions.
//
//    Note that the eval command has been disabled.
//
//    Concepts
//    The interpreter will take inputs in the language 
//    described under the language header below. 
//    This section will give an overview of the language constructs.
//
//    Variables
//    Any identifier which is not a keyword or a function name 
//    will be treated as a variable. 
//    If the identifier is on the left hand side of an assignment operator, 
//    the result of the right hand side will be stored in the variable. 
//    If a variable occurs as part of an expression, 
//    the value held in the variable will be substituted when the 
//    expression is evaluated.
//
//    Variables are implicitly declared the first time they are assigned to.
//
//    Example: Initializing a variable to a constant value and using 
//    the variable in another expression 
//    (Each line starting with a '>' indicates a separate call 
//    to the input method of the interpreter, other lines represent output)
//
//    >x = 7
//        7
//    >x + 6
//        13
//    Referencing a non-existent variable will cause the interpreter 
//    to throw an error. 
//    The interpreter should be able to continue accepting input even after throwing.
//
//    Example: Referencing a non-existent variable
//
//    >y + 7
//        ERROR: Invalid identifier. No variable with name 'y' was found."
//    Assignments
//    An assignment is an expression that has an identifier on left 
//    side of an = operator, and any expression on the right. 
//    Such expressions should store the value of the right hand side 
//    in the specified variable and return the result.
//
//    Example: Assigning a constant to a variable
//
//    x = 7
//        7
//    You should also be able to chain and nest assignments. 
//    Note that the assignment operator is one of the few that is right associative.
//
//    Example: Chained assignments. The statement below should set both x and y to 7.
//
//    x = y = 7
//        7
//    Example: Nested assignments. The statement below should set y to 3, 
//    but it only outputs the final result.
//
//    x = 13 + (y = 3)
//        16
//    Operator Precedence
//    Operator precedence will follow the common order. 
//    There is a table in the Language section below that 
//    explicitly states the operators and their relative precedence.
//
//    Functions
//    Functions are declared by the fn keyword followed by a name, 
//    an optional arguments list, the => operator, and finally an expression. 
//    All function variables are local to the function. That is, the only 
//    variable names allowed in the function body are those declared by the 
//    arguments list. If a function has an argument called 'x', and there is also 
//    a global variable called 'x', the function should use the value of the supplied 
//    argument, not the value of the global variable, when evaluating the expression. 
//    References to variables not found in the argument list should result in an error 
//    when the function is defined.
//
//    Example: declare a function to calculate the average of two variables and call it. 
//    (Each line starting with a '>' indicates a separate call to the input method of 
//    the interpreter, other lines represent output)
//
//    >fn avg => (x + y) / 2
//        ERROR: Unknown identifier 'x'
//    >fn avg x y => (x + y) / 2
//    >a = 2
//        2
//    >b = 4
//        4
//    >avg a b
//        3
//    Example: declare a function with an invalid variable name in the function body
//
//    >fn add x y => x + z
//        ERROR: Invalid identifier 'z' in function body.
//    Example: chain method calls (hint: function calls are right associative!)
//
//    >fn echo x => x
//    >fn add x y => x + y
//    >add echo 4 echo 3
//        7
//    Name conflicts
//    Because variable and function names share the same grammar, 
//    conflicts are possible. Precedence will be given to the first object declared. 
//    That is, if a variable is declared, then subsequent declaration of a function 
//    with the same name should result in an error. Likewise, declaration of a 
//    function followed by the initialization of a variable with the same name 
//    should result in an error.
//
//    Declaration of function with the same name as an existing function should 
//    overwrite the old function with the new one.
//
//    Example: Overwriting a function
//
//    >fn inc x => x + 1
//    >a = 0
//        0
//    >a = inc a
//        1
//    >fn inc x => x + 2
//    >a = inc a
//        3
//    Input
//    Input will conform to either the function production or the expression 
//    production in the grammar below.
//
//    Output
//    Output for a valid function declaration will be an empty string (null in Java).
//    Output for a valid expression will be the result of the expression.
//    Output for input consisting entirely of whitespace will be an empty string (null in Java).
//    All other cases will throw an error.
//    -- In Haskell that is:
//    Right (Nothing, Interpreter)
//    Right (Just Double, Interpreter) 
//    Right (Nothing, Interpreter)
//    Left String
//    Language
//    Grammar
//    This section specifies the grammar for the interpreter language in EBNF syntax
//
//    function        ::= fn-keyword fn-name { identifier } fn-operator expression
//    fn-name         ::= identifier
//    fn-operator     ::= '=>'
//    fn-keyword      ::= 'fn'
//
//    expression      ::= factor | expression operator expression
//    factor          ::= number | identifier | assignment | '(' expression ')' | function-call
//    assignment      ::= identifier '=' expression
//    function-call   ::= fn-name { expression }
//
//    operator        ::= '+' | '-' | '*' | '/' | '%'
//
//    identifier      ::= letter | '_' { identifier-char }
//    identifier-char ::= '_' | letter | digit
//
//    number          ::= { digit } [ '.' digit { digit } ]
//
//    letter          ::= 'a' | 'b' | ... | 'y' | 'z' | 'A' | 'B' | ... | 'Y' | 'Z'
//    digit           ::= '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9'
//    Operator Precedence
//    The following table lists the language's operators grouped in order of precedence. 
//    Operators within each group have equal precedence.
//
//    Category    Operators
//    Multiplicative  *, /, %
//    Additive    +, -
//    Assignment  =
//    Function    =>
//    
//    
//    
//    THE TASK IS SPECIALLY DONE WITHOUT USING POLISH NOTATION
//    FOR TRYING TO DEVELOP MY SKILLS IN CREATING MY OWN
//    ALGORITHMS
    
    
    Set<String> functionNames = null; 
    Set<String> varitiesNames = null; 
    Map<String, Integer> operatorsPrior = null; 
    Set<Variety> varities = null;
    Set<Function> functions = null;
    
    Set<String> currentVarities = null;
    
    Interpreter() {
        this.functionNames = new HashSet<String>(); 
        this.varitiesNames = new HashSet<String>(); 
        this.varities = new HashSet<Variety>(); 
        this.functions = new HashSet<Function>(); 
        this.currentVarities = new HashSet<String>();
        this.operatorsPrior = new HashMap<String, Integer>();
        this.operatorsPrior.put("*", 2);
        this.operatorsPrior.put("/", 2);
        this.operatorsPrior.put("%", 2);
        this.operatorsPrior.put("+", 1);
        this.operatorsPrior.put("-", 1);
        this.operatorsPrior.put("=", 0);
    }
    
    /**
     *  Whole the algorithm:
     *  
     *  1) if function command was involved (fn)
     *     -> creating function and adding it to 
     *        list of existing functions
     *  2) if expression
     *     -> firstly wrap all the operations
     *        with ( ) scopes for better counting
     *     -> wrap all the functions with [ ] scopes
     *        for further reworking of functions
     *     -> reworking functions: exchanging
     *        function name in calling on its 
     *        expression with appropriate 
     *        varieties
     *     -> solving whole the expression 
     * 
     * ERROR situations:
     * >fn avg => (x + y) / 2
     *     ERROR: Unknown identifier 'x'
     * >fn add x y => x + z
     *     ERROR: Invalid identifier 'z' in function body.
     * 
     * 
     *  */  
  public Double input(String input) {
    Deque<String> tokens = tokenize(input);
    String expression = "";
    boolean error = false;
    if (!tokens.isEmpty()) {
        String firstToken = tokens.getFirst();
        if (firstToken.equals("fn")) {
            try {
                functionCreate(tokens);
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                //System.out.println("input: '" + input + "'");
                error = true;
                throw e;
            }
        }
        else {
            // create an expression
            try {
                expression = expressionCreate(tokens);
                // check expression syntactically
                expression = expressionSynCheck(expression);
                // solve expression
                expression = expressionSolve(expression);
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                //System.out.println("input: '" + input + "'");
                error = true;
                throw e;
            }
        }  
    }
    
    if (!expression.isEmpty() && !error) {
        return Double.parseDouble(expression);
    }
    else
        return null;
  }
  
  /**
   * Function creation
   * 
   * ERROR situations:
   * >fn avg => (x + y) / 2
   *     ERROR: Unknown identifier 'x'
   * >fn add x y => x + z
   *     ERROR: Invalid identifier 'z' in function body.
   * 
   * 
   *  */
  private void functionCreate(Deque<String> tokens) {
      boolean functionName = false;
      String fnName = new String();
      boolean functionExpression = false;
      StringBuilder fnExpression = new StringBuilder();
      boolean functionVarities = false;
      StringBuilder fnVarities = new StringBuilder();
      Set<String> varities = new HashSet<String>();
      String errorText = new String();
      while (!tokens.isEmpty()) {
          String token = tokens.poll();
          
          // function definition
          if (token.equals("fn")) {
              functionName = true;
              functionExpression = true;
              functionVarities = true;
          }
          else {
              if (!functionVarities && functionExpression) {
                  
                  if (token.matches("[A-Za-z_][A-Za-z0-9_]*")) {
                      // variable check
                      if (!varities.contains(token) &&
                              !varities.isEmpty()) {
                          // ERROR
                          errorText += "ERROR: Invalid identifier '" + token 
                                  + "' in function body.";
                          break;
                      }
                      if (!varities.contains(token) &&
                              varities.isEmpty()) {
                          // ERROR
                          errorText += "ERROR: Unknown identifier '" + token + "'";
                          break;
                      }
                  }
                  
                  fnExpression.append(token);
                  fnExpression.append(" ");
              }
              if (!functionName && functionVarities) {
                  if (token.equals("=>")) {
                      functionVarities = false;
                  }
                  else {
                      if (varities.contains(token)) {
                          // ERROR
                          errorText += "ERROR: Varity '" + token 
                                  + "' is already defined in function.";
                          break;
                      }
                      else {
                          varities.add(token);
                      }
                      fnVarities.append(token);
                      fnVarities.append(" ");
                  }
              }
              if (functionName) {
                  fnName = token;
                  if (this.varitiesNames.contains(fnName)) {
                      // ERROR
                      errorText += "ERROR: Varity '" + token 
                              + "' is defined with such name.";
                      break;
                  }
                  functionName = false;
              }
          }
      }   
      if (!errorText.equals("")) {
          throw new IllegalArgumentException(errorText);
      }
      else {
          Function function = new Function(fnName, varities, fnExpression.toString());
          // overwriting a function
          if (this.functionNames.contains(fnName)) {
              for (Function func: this.functions) {
                  if (func.functionName.equals(fnName)) {
                      this.functions.remove(func);
                      break;
                  }
              }
          }
          this.functionNames.add(fnName);
          this.functions.add(function);
      }
  }
  
  /**
   * Add all function calls in [ ] scopes and exchange 
   * function call on its expression
   * 
   *  */
  private String functionExpressionResolve(Deque<String> tokens) {
      // adding all functions to [ ] scopes
      String mainExpression = functionExpressionScopesResolve(tokens);
      // exchanging all functions on its expression
      mainExpression = functionExpressionExchange(mainExpression);
      return mainExpression;
  }
  
  /**
   * Add all function calls in [ ] scopes for further parsing
   * 
   *  */
  private String functionExpressionScopesResolve(Deque<String> tokens) {
      Stack<String> names = new Stack<String>();
      Stack<String> operators = new Stack<String>();
      
      // gather whole expression for further reworking
      StringBuilder expression = new StringBuilder();
      Deque <Integer> functionsVars = new LinkedList<Integer>();
      int curFuncVarities = -1;
      while (!tokens.isEmpty()) {
          String token = tokens.poll();
          
          if (token.matches("[A-Za-z_][A-Za-z0-9_]*")) {      
              names.push(token);
              if (this.functionNames.contains(token)) {                
                  for (Function func: this.functions) {
                      if (func.functionName.equals(token)) {
                          functionsVars.addLast(func.varities.size());
                          break;
                      }
                  }
                  expression.append("[ ");
                  expression.append(token);
                  expression.append(" ");
              }
              else {
                  // check if function variables
                  if (!functionsVars.isEmpty()) {
                      
                      if (!functionsVars.isEmpty()) {
                          int last = functionsVars.removeLast();
                          functionsVars.addLast(last-1);
                      }
                      
                      curFuncVarities = functionsVars.peekLast();
                  }
                  else {
                      curFuncVarities = -1;
                  }
                    expression.append(token);
                    expression.append(" ");
                    
                    if (curFuncVarities == 0) {
                        expression.append("] ");
                        functionsVars.removeLast();
                        if (!functionsVars.isEmpty()) {
                            int last = functionsVars.removeLast();
                            functionsVars.addLast(last-1);
                        }
                        curFuncVarities = -1;
                    }
              }      
          }
          else if (token.matches("[-+*/%=\\(\\)]")) {
              operators.push(token);
              
           // check if function variables
              if (!functionsVars.isEmpty()) {
                  curFuncVarities = functionsVars.peekLast();
              }
              
              if (curFuncVarities == 0 && !token.equals("=")) {
                  expression.append("] ");
                  functionsVars.removeLast();
                  if (!functionsVars.isEmpty()) {
                      int last = functionsVars.removeLast();
                      functionsVars.addLast(last-1);
                  }
                  curFuncVarities = -1;
              }
              
              expression.append(token);
              expression.append(" ");
          }
          else if (token.matches("[0-9]*(\\.?[0-9]+)")) {
              // check if function variables
              if (!functionsVars.isEmpty()) {
            
                  if (!functionsVars.isEmpty()) {
                      int last = functionsVars.removeLast();
                      functionsVars.addLast(last-1);
                  }
                  
                  curFuncVarities = functionsVars.peekLast();
              }
              else {
                  curFuncVarities = -1;
              }

                expression.append(token);
                expression.append(" ");

                if (curFuncVarities == 0) {
                    expression.append("] ");
                    functionsVars.removeLast();
                    if (!functionsVars.isEmpty()) {
                        int last = functionsVars.removeLast();
                        functionsVars.addLast(last-1);
                    }
                    curFuncVarities = -1;
                }
          }
      }
      // check if function variables
      if (!functionsVars.isEmpty()) {
          if (functionsVars.peekLast() == 0) {
              expression.append("] ");
              functionsVars.removeLast();
          }
      }
      
      return expression.toString();
  }
  
  /**
   * Function expression resolving
   * (on the previous step all the function were added to [ ] scopes)
   *  */
  private String functionExpressionExchange(String expression) {
      String[] tokens = expression.split(" ");
      
      Stack<String> functions = new Stack<String>();
      Stack<String> values = new Stack<String>();
      StringBuilder funcInExpression = new StringBuilder();
      int countScopes = 0;
      for (String token: tokens) {
           if (token.matches("[\\[\\]]")) {
               if (token.matches("\\[")) {
                   if (countScopes == 0) {
                       funcInExpression = new StringBuilder();
                   }
                   countScopes++;
               }
               if (token.matches("\\]")) {
                   String fn = functions.peek();
                   Function curFunc = null;
                   for (Function func: this.functions) {
                       if (func.functionName.equals(fn)) {
                           curFunc = func;
                           break;
                       }
                   }
                   String functionModifiedExpression = curFunc.expression;
                   if (!functions.isEmpty() &&
                           !values.isEmpty()) {
                       for (int i = curFunc.varities.size() - 1; i >= 0; i--) {
                           String val1 = values.pop();
                           functionModifiedExpression = functionModifiedExpression
                                                        .replaceAll(curFunc.varities.get(i), val1);
                       }
                   }
                   if (!functions.isEmpty()) {
                       functions.pop();
                   }
                   values.push(functionModifiedExpression.trim());
                   countScopes--;
                   if (countScopes == 0) {
                       // replace result
                       funcInExpression.append("\\]");
                       String result = "";
                       if (!values.isEmpty()) {
                           if (values.peek().split("\\s").length > 1)
                               result = "( " + values.pop() + " )";
                           else
                               result = values.pop();
                       }
                       expression = expression.replaceAll(funcInExpression.toString().trim(), result);
                       funcInExpression = new StringBuilder();
                   }
               }
           }
           else if (token.matches("[A-Za-z_][A-Za-z0-9_]*|[0-9]*(\\.?[0-9]+)")) {
               if (this.functionNames.contains(token)) {
                   functions.push(token);
               }
               else {
                   values.push(token);
               }
           }
           if (countScopes > 0) {
               if (token.matches("\\[")) {
                   funcInExpression.append("\\[");
               }
               else if (token.matches("\\]")) {
                   funcInExpression.append("\\]");
               }
               else funcInExpression.append(token);
               funcInExpression.append(" ");
           }
      }
      return expression;
  }
  
  
  /**
   * Expression creation and varieties addition
   *  */
  private String expressionCreate(Deque<String> tokens) {     
      // exchange all functions to its expressions
      String mainExpression = functionExpressionResolve(tokens);
      
      return mainExpression;
  } 
  
  /**
   * Expression syntactically check
   *  */
  private String expressionSynCheck(String mainExpression) {
      
      if (mainExpression.contains("[") ||
              mainExpression.contains("]")) {
          throw new IllegalArgumentException("Wrong function call");
      }
      
      Deque<String> tokens = tokenize(mainExpression);
      boolean operatorFound = true;
      for (String token: tokens) {
          if (token.matches("[-+=*/%\\(\\)]")) {
              if (token.matches("[-+=*/%]")) {
                  operatorFound = true;
              }
          }
          else if (token.matches("[A-Za-z_][A-Za-z0-9_]*|[0-9]*(\\.?[0-9]+)")) {
              if (operatorFound) {
                  operatorFound = false;
              }
              else {
                  throw new IllegalArgumentException("Wrong expression");
              }
          }
      }
      
      return "( " + mainExpression.trim() + " )";
  }
  
  /**
   * Expression resolving
   *   - values and operators put to the stacks
   *   - if met operator with lower priority than
   *     one on the top of the stack or ) operator
   *      -> popping two values and previous operator
   *         solving and put result to values stack
   *   - in evaluate function varieties are replaced
   *     by its values
   *  */
  private String expressionSolve(String expression) {
      String[] tokens = expression.split(" ");
      
      Stack<String> operators = new Stack<String>();
      Stack<String> values = new Stack<String>();
      
      for (String token: tokens) {
           if (token.matches("[-+=*/%\\(\\)]")) {
               if (!operators.isEmpty() &&
                           !values.isEmpty()) {
                   String op = operators.peek();
                   if (!op.matches("[\\(\\)]") && !token.matches("[\\(\\)]")) {
                       while (!operators.isEmpty() &&
                               !op.matches("[\\(\\)]") &&
                               operatorsPrior.get(op) >= 
                               operatorsPrior.get(token)) {
                           String val2 = values.pop();
                           String val1 = values.pop();
                           operators.pop();
                           String result = evaluate(op, val1, val2);
                           values.push(result);
                           if (!operators.isEmpty())
                               op = operators.peek();
                           else
                               break;
                       }
                   }  
                }
               
               if (token.equals(")") && values.size() > 1) {
                   String val2 = values.pop();
                   String val1 = values.pop();
                   String op = operators.pop();
                   String result = evaluate(op, val1, val2);
                   values.push(result);
                   
                   if (!operators.isEmpty() &&
                               !values.isEmpty()) {
                       op = operators.peek();
                       //op = operators.pop();
                       while (!op.matches("[\\(]")) {
                           val2 = values.pop();
                           val1 = values.pop();
                           operators.pop();
                           result = evaluate(op, val1, val2);
                           values.push(result);
                           if (!operators.isEmpty())
                              op = operators.peek();
                           else
                               break;
                       }
                       if (op.matches("[\\(]")) {
                           operators.pop();
                       }
                   }
               }
               else
                   operators.push(token);   
           }
           else if (token.matches("[A-Za-z_][A-Za-z0-9_]*|[0-9]*(\\.?[0-9]+)")) {
               values.push(token);
           }
      }
      
      if (!values.isEmpty()) {
          if (values.peek().matches("[A-Za-z_][A-Za-z0-9_]*")) {
              return findVarietyValue(values.peek());
          }
          else
              return values.pop();
      }
      else {
          return null;
      }
  }

  /**
   * Evaluate one operator
   *  */
  private String evaluate(String operator, String val1, String val2) {
      String result = "";
      
      if (val1.matches("[A-Za-z_][A-Za-z0-9_]*") &&
              !operator.equals("=")) {
          // replacing variable on its value
          if (this.varitiesNames.contains(val1)){
              val1 = findVarietyValue(val1);
          }
          else {
              // ERROR
          }
      }
      if (val2.matches("[A-Za-z_][A-Za-z0-9_]*") &&
              !operator.equals("=")) {
          // replacing variable on its value
          if (this.varitiesNames.contains(val2)){
              val2 = findVarietyValue(val2);
          }
          else {
              // ERROR
          }
      }
      
      if (operator.equals("+")) {
          double value = Double.parseDouble(val1) +
                         Double.parseDouble(val2);
          result = String.valueOf(value);
      }
      if (operator.equals("-")) {
          double value = Double.parseDouble(val1) -
                         Double.parseDouble(val2);
          result = String.valueOf(value);
      }
      if (operator.equals("*")) {
          double value = Double.parseDouble(val1) *
                         Double.parseDouble(val2);
          result = String.valueOf(value);
      }
      if (operator.equals("/")) {
          double value = Double.parseDouble(val1) /
                         Double.parseDouble(val2);
          result = String.valueOf(value);
      }
      if (operator.equals("%")) {
          double value = Double.parseDouble(val1) %
                         Double.parseDouble(val2);
          result = String.valueOf(value);
      }
      if (operator.equals("=")) {
          if (!val2.matches("[A-Za-z_][A-Za-z0-9_]*")) {
                // if such variable already exists
                if (this.currentVarities.contains(val1)) {
                    for (String varName: this.currentVarities) {    
                        if (this.varitiesNames.contains(varName)) {
                            for (Variety var: this.varities) {
                                if (var.varietyName.equals(varName)) {
                                    var.varietyValue = String.valueOf(val2);
                                    break;
                                }
                            }
                        }
                        else {
                            Variety newVar = new Variety(varName, val2);
                            this.varities.add(newVar);
                            this.varitiesNames.add(varName);
                        }
                    }
                    result = String.valueOf(val2);
                    this.currentVarities.clear();
                }
                // if no such variable exists
                else {
                    if (this.functionNames.contains(val1)) {
                        throw new IllegalArgumentException("Function '" + val1 + 
                                "' with such name already exists");
                    }
                    if (this.varitiesNames.contains(val1)) {
                        for (Variety var: this.varities) {
                            if (var.varietyName.equals(val1)) {
                                var.varietyValue = String.valueOf(val2);
                                result = String.valueOf(val2);
                                break;
                            }
                        }
                    }
                    else {
                        Variety newVar = new Variety(val1, val2);
                        this.varities.add(newVar);
                        this.varitiesNames.add(val1);
                        result = String.valueOf(val2);
                    }
                }
            }
            else {
                this.currentVarities.add(val1);
                this.currentVarities.add(val2);
                result = String.valueOf(val2);
            }
      }
      return result;
  }
  
  /**
   * Evaluate one operator
   *  */
  private String findVarietyValue(String varName) {
      String result = "";
      Variety variety = null;
      if (this.varitiesNames.contains(varName)){
          for (Variety var: this.varities) {
              if (var.varietyName.equals(varName)) {
                  variety = var;
                  result = var.varietyValue;
                  break;
              }
          }
      }
      if (result.matches("[A-Za-z_][A-Za-z0-9_]*")) {
          result = findVarietyValue(result);
      }
      if (variety != null)
          variety.varietyValue = result;
      else 
          throw new IllegalArgumentException("No such variable: " + varName);
      return result;
  }
  
  private static Deque<String> tokenize(String input) {
    Deque<String> tokens = new LinkedList<>();
    Pattern pattern = Pattern.compile("=>|[-+*/%=\\(\\)]|[A-Za-z_][A-Za-z0-9_]*|[0-9]*(\\.?[0-9]+)");
    Matcher m = pattern.matcher(input);
    while (m.find()) {
      tokens.add(m.group());
    }
    return tokens;
  }
  
  class Function {
      public String functionName = "";
      public List<String> varities = null;
      public String expression = "";
      
      Function (String functionName, Set<String> varities, String expression) {
          this.functionName = functionName;
//          String[] vars = varities.split(" ");
//          for (String var: vars) {
//              this.varities.add(var);
//          }
          this.varities = new ArrayList<String>();
          for (String var: varities) {
                this.varities.add(var);
          }
          this.expression = expression;
      }
  }
  class Variety {
      public String varietyName = "";
      public String varietyValue = "";
      Variety (String varietyName, String varietyValue) {
          this.varietyName = varietyName;
          this.varietyValue = varietyValue;
      }
  }
  
  public static void main(String args[]) throws Exception {
      Interpreter interpreter = new Interpreter();
      
      System.out.println("Special difficult cases:");
      System.out.println(interpreter.input("( 6 + 9 - 5 ) / ( 8 + 1 * 2 ) + 7"));
      
      
      System.out.println("Special difficult cases:");
      System.out.println(interpreter.input("1 + 2 * ( 3 + 4 / 2 - ( 1 + 2 ) ) * 2 + 1"));
      System.out.println(interpreter.input("( 6 + 7 ) * 2"));
      System.out.println(interpreter.input("4 + 2 * 3"));
      System.out.println(interpreter.input("5 + 1 * 2 * 3 * 4"));
      System.out.println(interpreter.input("( 5 + 1 ) * 2 * 3 * 4"));
      System.out.println(interpreter.input("5 + ( 1 + 2 * ( 3 * 4 + 5 ) )"));
      
      System.out.println("Cases with '=':");
      System.out.println(interpreter.input("b = c = d = 4 + 2 * (3 + 1)"));
      interpreter.input("fn inc x => x + 1");
      interpreter.input("a = 0");
      System.out.println(interpreter.input("3 + (a = e = inc a)*3"));
      
      System.out.println("Test 1:");
      System.out.println(interpreter.input("fn avg x y => (x + y) / 2"));
      System.out.println(interpreter.input("6 + (y = 5) + 7"));
      System.out.println(interpreter.input("a = 2"));
      System.out.println(interpreter.input("b = 4 + 2 * (3 + 1)"));
      System.out.println(interpreter.input("avg a b + avg 3 y")); // 7.0
      System.out.println(interpreter.input("a * b + y"));
      
      // test 2
      System.out.println("Test 2:");
      interpreter = new Interpreter();
      interpreter.input("fn avg x y => (x + y) / 2");
      System.out.println(interpreter.input("avg 4 2")); //3
      
      // test 3
      System.out.println("Test 3:");
      interpreter = new Interpreter();
      System.out.println(interpreter.input("7 % 4")); //3
      
      // test 4 !!!
      //>fn echo x => x
      //>fn add x y => x + y
      //>add echo 4 echo 3 // answer: 7
      System.out.println("Test 4:");
      interpreter = new Interpreter();
      interpreter.input("fn echo x => x");
      interpreter.input("fn add x y => x + y");
      System.out.println(interpreter.input("add echo 4 echo 3"));
      
      // test 5 !!! (overwriting a function and variable)
      //>fn inc x => x + 1
      //>a = 0
      //    0
      //>a = inc a
      //    1
      //>fn inc x => x + 2
      //>a = inc a
      //    3
      System.out.println("Test 5:");
      interpreter = new Interpreter();
      interpreter.input("fn inc x => x + 1");
      interpreter.input("a = 0");
      System.out.println(interpreter.input("a = inc a"));
      interpreter.input("fn inc x => x + 2");
      System.out.println(interpreter.input("a = inc a"));
    
      // test 6 !!! (overwriting a function and variable)
      System.out.println("Test 6:");
      interpreter = new Interpreter();
      interpreter.input("fn inc x => x + 1");
      interpreter.input("a = 0");
      System.out.println(interpreter.input("3 + (a = inc a)*3"));
      interpreter.input("fn inc x => x + 2");
      System.out.println(interpreter.input("((a = inc a)-12)*2"));
      
      interpreter = new Interpreter();
      interpreter.input("fn echo x => x");
      interpreter.input("fn add x y => x + y");
      System.out.println(interpreter.input("add echo 4 echo 3"));  
      System.out.println(interpreter.input("add 4 4 + add echo 4 echo 3 + echo 5"));
      
      // test 7 (error occurences)
      System.out.println("Test 7:");
      interpreter = new Interpreter();
      interpreter.input("fn avg => (x + y) / 2");
      interpreter.input("fn add x y => x + z");    
      
      // Basic test
      // Basic arithmetic
      System.out.println("Basic arithmetic:");
      System.out.println(interpreter.input("1 + 1"));  // 2
      System.out.println(interpreter.input("2 - 1")); // 1
      System.out.println(interpreter.input("2 * 3")); // 6
      System.out.println(interpreter.input("8 / 4")); // 2
      System.out.println(interpreter.input("7 % 4")) ; // 3

      // Variables
      System.out.println("Variables:");
      System.out.println(interpreter.input("x = 1")); // 1
      System.out.println(interpreter.input("x")); // 1
      System.out.println(interpreter.input("x + 3")); // 4

      // Functions
      System.out.println("Functions:");
      interpreter.input("fn avg x y => (x + y) / 2");
      System.out.println(interpreter.input("avg 4 2")); // 3

      // Conflicts
      System.out.println("Conflicts:");
      interpreter.input("y");
      
      interpreter.input("avg 7");
      interpreter.input("avg 7 2 4");
      
      interpreter.input("fn x => 0");
      interpreter.input("avg = 5"); 
      
      // Attempt tests
      System.out.println("Attempt tests:");
      interpreter = new Interpreter();
      System.out.println("complexExpressions :");
      
      System.out.println("conflicts:");
      System.out.println(interpreter.input("x = 0"));  
      System.out.println(interpreter.input("fn f => 1")); 
      System.out.println(interpreter.input("fn x => 0"));
      System.out.println(interpreter.input("f = 5"));
      
      System.out.println("functions:");
      System.out.println(interpreter.input("x = 23"));  
      System.out.println(interpreter.input("y = 25"));  
      System.out.println(interpreter.input("z = 0"));  
      System.out.println(interpreter.input("fn one => 1"));
      System.out.println(interpreter.input("fn avg x y => (x + y) / 2"));  
      System.out.println(interpreter.input("fn echo x => x")); 
      System.out.println(interpreter.input("fn add x y => x + z")); 
      System.out.println(interpreter.input("fn add x x => x + x")); 
      System.out.println(interpreter.input("(fn f => 1)")); 
      System.out.println(interpreter.input("one")); 
      System.out.println(interpreter.input("avg 4 2")); 
      System.out.println(interpreter.input("avg 7")); 
      System.out.println(interpreter.input("avg 7 2 4")); 
      System.out.println(interpreter.input("avg echo 4 echo 2")); 
      System.out.println(interpreter.input("avg echo 7")); 
      System.out.println(interpreter.input("avg echo 7 echo 2 echo 4")); 
      
      System.out.println("variables:");
      System.out.println(interpreter.input("x = 7"));  
      System.out.println(interpreter.input("x"));  
      System.out.println(interpreter.input("x + 3"));  
      System.out.println(interpreter.input("y"));  
      System.out.println(interpreter.input("y = x + 5"));  
      System.out.println(interpreter.input("y"));  
      System.out.println(interpreter.input("x = y = 713"));  
      System.out.println(interpreter.input("x"));  
      System.out.println(interpreter.input("y"));
      
      
      System.out.println("basicExpressions:");
  }
}
