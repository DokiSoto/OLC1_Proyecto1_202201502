package expr;

import engine.BattleScriptExecutionException;
import java.util.ArrayList;
import java.util.List;
import Modelo.Accion;

/**
 *
 * @author dxssm
 */
public interface Expresion {

    Object eval(EvalContext context);

    class Literal implements Expresion {

        private final Object value;

        public Literal(Object value) {
            this.value = value;
        }

        @Override
        public Object eval(EvalContext context) {
            return value;
        }
    }
}

class Variable implements Expresion {

    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public Object eval(EvalContext context) {
        return context.get(name);
    }
}

class Not implements Expresion {

    private final Expresion expression;

    public Not(Expresion expression) {
        this.expression = expression;
    }

    @Override
    public Object eval(EvalContext context) {

        Object value = expression.eval(context);

        if (!(value instanceof Boolean)) {
            throw new BattleScriptExecutionException(
                    "xd, ocurrio un error en algo"
            );
        }

        return !((Boolean) value);
    }
}

class Binary implements Expresion {

    private final String operator;
    private final Expresion left;
    private final Expresion right;

    public Binary(String operator, Expresion left, Expresion right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public Object eval(EvalContext context) {

        
        if ("&&".equals(operator)) {

            Object leftValue = left.eval(context);

            if (!(leftValue instanceof Boolean)) {
                throw new BattleScriptExecutionException(
                        "ocurrio un error en algo"
                );
            }

            if (!((Boolean) leftValue)) {
                return false;
            }

            Object rightValue = right.eval(context);

            if (!(rightValue instanceof Boolean)) {
                throw new BattleScriptExecutionException(
                        "ocurrio un error en algo"
                );
            }

            return (Boolean) rightValue;
        }

      
        if ("||".equals(operator)) {

            Object leftValue = left.eval(context);

            if (!(leftValue instanceof Boolean)) {
                throw new BattleScriptExecutionException(
                        "ocurrio un error en algo"
                );
            }

            if ((Boolean) leftValue) {
                return true;
            }

            Object rightValue = right.eval(context);

            if (!(rightValue instanceof Boolean)) {
                throw new BattleScriptExecutionException(
                        "ocurrio un error en algo"
                );
            }

            return (Boolean) rightValue;
        }

        Object a = left.eval(context);
        Object b = right.eval(context);

        switch (operator) {

            case "==":
                return equalsValue(a, b);

            case "!=":
                return !equalsValue(a, b);

            case ">":
                return number(a) > number(b);

            case "<":
                return number(a) < number(b);

            case ">=":
                return number(a) >= number(b);

            case "<=":
                return number(a) <= number(b);

            default:
                throw new BattleScriptExecutionException(
                        "ocurrio un error en algo"
                );
        }
    }

    private Boolean equalsValue(Object a, Object b) {

        if (a instanceof Number && b instanceof Number) {
            return Double.compare(
                    ((Number) a).doubleValue(),
                    ((Number) b).doubleValue()
            ) == 0;
        }

        if (a == null) {
            return b == null;
        }

        return a.equals(b);
    }

    private double number(Object value) {

        if (!(value instanceof Number)) {
            throw new BattleScriptExecutionException(
                    "ocurrio un error en algo"
            );
        }

        return ((Number) value).doubleValue();
    }
}

class Function implements Expresion {

    private final String name;
    private final List<Expresion> arguments;

    public Function(String name, List<Expresion> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    @Override
    public Object eval(EvalContext context) {

        // get_move
        if ("get_move".equals(name)) {

            List<Accion> history = actionList(
                    arguments.get(0).eval(context)
            );

            int index = integer(
                    arguments.get(1).eval(context)
            );

            if (index < 0 || index >= history.size()) {
                throw new BattleScriptExecutionException(
                        "ocurrio un error en algo"
                );
            }

            return history.get(index);
        }

        if ("last_move".equals(name)) {

            List<Accion> history = actionList(
                    arguments.get(0).eval(context)
            );

            if (history.isEmpty()) {
                throw new BattleScriptExecutionException(
                        "ocurrio un error en algo"
                );
            }

            return history.get(history.size() - 1);
        }

        if ("get_moves_count".equals(name)) {

            List<Accion> history = actionList(
                    arguments.get(0).eval(context)
            );

            Object actionValue = arguments.get(1).eval(context);

            if (!(actionValue instanceof Accion)) {
                throw new BattleScriptExecutionException(
                        "ocurrio un error en algo"
                );
            }

            Accion action = (Accion) actionValue;

            int count = 0;

            for (Accion current : history) {

                if (current == action) {
                    count++;
                }
            }

            return count;
        }

        if ("get_last_n_moves".equals(name)) {

            List<Accion> history = actionList(
                    arguments.get(0).eval(context)
            );

            int n = integer(
                    arguments.get(1).eval(context)
            );

            if (n <= 0) {
                throw new BattleScriptExecutionException(
                        "get_last_n_moves: n debe ser mayor que cero."
                );
            }

            if (n > history.size()) {
                throw new BattleScriptExecutionException(
                        "get_last_n_moves: no existen suficientes movimientos."
                );
            }

            return new ArrayList<>(
                    history.subList(
                            history.size() - n,
                            history.size()
                    )
            );
        }

        throw new BattleScriptExecutionException(
                "Función desconocida: " + name
        );
    }

    @SuppressWarnings("unchecked")
    private List<Accion> actionList(Object value) {

        if (!(value instanceof List)) {
            throw new BattleScriptExecutionException(
                    "Se esperaba un historial o lista de acciones."
            );
        }

        return (List<Accion>) value;
    }

    private int integer(Object value) {

        if (!(value instanceof Number)) {
            throw new BattleScriptExecutionException(
                    "Se esperaba un entero."
            );
        }

        return ((Number) value).intValue();
    }
}
             
       
