//Revisado
package interpreter.expr;

import interpreter.util.Utils;
import interpreter.value.BoolValue;
import interpreter.value.NumberValue;
import interpreter.value.Value;

public class UnaryExpr extends Expr {

    private Expr expr;
    private UnaryOp op;

    public UnaryExpr(int line, Expr expr, UnaryOp op) {
        super(line);
        this.expr = expr;
        this.op = op;
    }

    @Override
    public Value<?> expr() {
        switch (op) {
            case NEG:
                return negOp();
            case NOT:
                return notOp();
            case PRE_INC:
                return preIncOp();
            case POS_INC:
                return posIncOp();
            case PRE_DEC:
                return preDecOp();
            case POS_DEC:
                return posDecOp();
            default:
                Utils.abort(super.getLine());
                return null;
        }
    }

    private Value<?> negOp() {
        Value<?> v = expr.expr();
        if (v instanceof NumberValue) {
            NumberValue nv = (NumberValue) v;
            int n = nv.value();
            int res = -n;
            NumberValue nres = new NumberValue(res);
            return nres;
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }
    
    private Value<?> notOp() {
        Value<?> v = expr.expr();
        if (v instanceof BoolValue) {
            BoolValue bv = (BoolValue) v;
            boolean b = bv.value();
            boolean res = !b;
            BoolValue bres = new BoolValue(res);
            return bres;
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }
    
    private Value<?> preIncOp() {//PELUCIVIA
        Value<?> v = expr.expr();
        if (v instanceof NumberValue) {
            NumberValue nv = (NumberValue) v;
            int n = nv.value();
            int res = ++n;        
            if (expr instanceof SetExpr){
                ((SetExpr) expr).setValue(new NumberValue(res));
            }
            NumberValue nres = new NumberValue(res);
            return nres;
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }
    
    private Value<?> posIncOp() {//PELUCIVIA
        Value<?> v = expr.expr();
        if (v instanceof NumberValue) {
            NumberValue nv = (NumberValue) v;
            int n = nv.value();
            int res = n++;

            if (expr instanceof SetExpr){
                ((SetExpr) expr).setValue(new NumberValue(n));
            }

            NumberValue nres = new NumberValue(res);
            return nres;
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }
    
    private Value<?> preDecOp() {//PELUCIVIA
        Value<?> v = expr.expr();
        if (v instanceof NumberValue) {
            NumberValue nv = (NumberValue) v;
            int n = nv.value();
            int res = --n;
            if (expr instanceof SetExpr){
                ((SetExpr) expr).setValue(new NumberValue(res));
            }

            NumberValue nres = new NumberValue(res);
            return nres;
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }
    
    private Value<?> posDecOp() {//PELUCIVIA
        Value<?> v = expr.expr();
        if (v instanceof NumberValue) {
            NumberValue nv = (NumberValue) v;
            int n = nv.value();
            int res = n--;
            if (expr instanceof SetExpr){
                ((SetExpr) expr).setValue(new NumberValue(n));
            }
            NumberValue nres = new NumberValue(res);
            return nres;
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }
    
}
