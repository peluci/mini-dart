//Falta bastante coisa
package interpreter.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import interpreter.util.Utils;
import interpreter.value.BoolValue;
import interpreter.value.ListValue;
import interpreter.value.MapValue;
import interpreter.value.NumberValue;
import interpreter.value.TextValue;
import interpreter.value.Value;

public class FunctionExpr extends Expr {

    private FunctionOp op;
    private Expr expr;

    private static Scanner input = new Scanner(System.in);
    private static Random rand = new Random();
    
    public FunctionExpr(int line, FunctionOp op, Expr expr) {
        super(line);

        this.op = op;
        this.expr = expr;
    }

    @Override
    public Value<?> expr() {
        Value<?> v = expr.expr();

        switch (op) {
            case READ:
                return readOp(v);
            case RANDOM:
                return randomOp(v);
            case LENGTH:
                return lengthOp(v);
            case KEYS:
                return keysOp(v);
            case VALUES:
                return valuesOp(v);
            case TOBOOL:
                return toBoolOp(v);
            case TOINT:
                return toIntOp(v);
            case TOSTR:
                return toStrOp(v);
            default:
                Utils.abort(super.getLine());
                return null;
        }
    }

    private TextValue readOp(Value<?> v) {
        System.out.print(v);

        String text = input.nextLine().trim();
        return text.isEmpty() ? null : new TextValue(text);
    }

    private NumberValue randomOp(Value<?> v) {//utils
        int n=0;
        
        if (v instanceof NumberValue){
            NumberValue nv = (NumberValue) v;
            int value = nv.value();
            n = rand.nextInt(value);
        }
        else{
            Utils.abort(super.getLine());
        }
        return new NumberValue(n);
    }

    private NumberValue lengthOp(Value<?> v) {//O que faz/como faz lenght? Mede a string?
        NumberValue res = null;
        if (v instanceof ListValue) {
            ListValue lv = (ListValue) v;
            res = new NumberValue(lv.value().size());
        }else{
            Utils.abort(super.getLine());
        }
        return res;
    }

    private ListValue keysOp(Value<?> v) {// O que faz values
        ListValue res = null;
        if(v instanceof MapValue) {
            MapValue mapv = (MapValue) v; 
            Map<Value<?>, Value<?>> map = mapv.value();
            Set<Value<?>> set = map.keySet();
            List<Value<?>> list = new ArrayList<Value<?>>(set);
            List<Value<?>> finalList = new ArrayList<Value<?>>();
            for(int i=0; i < list.size(); i++) {
    			finalList.add(list.get(i));
            }
            res = new ListValue(finalList);
        }
        else {
            Utils.abort(super.getLine());
        }
        return res;
    }

    private ListValue valuesOp(Value<?> v) {// O que faz values
            ListValue res = null;
            if(v instanceof MapValue) {
                MapValue mapv = (MapValue) v; 
                Map<Value<?>, Value<?>> map = mapv.value();
                Set<Value<?>> set1 = map.keySet();
                List<Value<?>> list = new ArrayList<Value<?>>(set1);
                List<Value<?>> list2 = new ArrayList<Value<?>>();
                for(int i=0; i < list.size(); i++) {
                    list2.add(map.get(list.get(i)));
                }
                res = new ListValue(list2);
            }
            else {
                Utils.abort(super.getLine());
            }
            return res;
        }

    private BoolValue toBoolOp(Value<?> v) {//Parecido com toint, mas como faz?
        boolean res=true;
        if (v == null) {
            res = false;
        } else if (v instanceof BoolValue) {
            BoolValue bv = (BoolValue) v;
            boolean b = bv.value();
            if (!b){
                res = false;
            }
        } else if (v instanceof NumberValue) {
            NumberValue nv = (NumberValue) v;
            if (nv.value()==0)
            res = false;
        } else if (v instanceof TextValue) {
            TextValue sv = (TextValue) v;
            String s = sv.value();
            if (s.length()==0)
            res = false;
        } else if (v instanceof ListValue){
            ListValue lv = (ListValue) v;
            if(lv.value().isEmpty()){
                res = false;
            }
        } else if (v instanceof MapValue){
            MapValue mv = (MapValue) v;
            if (mv.value().isEmpty()){
                res = false;
            }
        }
        else{
            res = true;
        }
        return new BoolValue(res);
    }

    private NumberValue toIntOp(Value<?> v) {
        int n;
        if (v == null) {
            n = 0;
        } else if (v instanceof BoolValue) {
            BoolValue bv = (BoolValue) v;
            boolean b = bv.value();

            n = b ? 1 : 0;
        } else if (v instanceof NumberValue) {
            NumberValue nv = (NumberValue) v;
            n = nv.value();
        } else if (v instanceof TextValue) {
            TextValue sv = (TextValue) v;
            String s = sv.value();

            try {
                n = Integer.parseInt(s);
            } catch (Exception e) {
                n = 0;
            }
        } else {
            n = 0;
        }

        return new NumberValue(n);
    }

    private TextValue toStrOp(Value<?> v) {//Explica?
        String t =null;
        if (v == null) {
            t = "null";
        }if (v instanceof BoolValue) {
            BoolValue bv = (BoolValue) v;
            boolean b = bv.value();
            if(b == false){
                t = "false"; 
            }else {
                t = "true";
            }
        } else if (v instanceof NumberValue) {
            NumberValue nv = (NumberValue) v;
            t = nv.toString();
        } else if (v instanceof TextValue) {
            TextValue tv = (TextValue) v;
            t = tv.value();
        } else if (v instanceof ListValue){
            ListValue lv = (ListValue) v;
            t = lv.toString();
        } else if (v instanceof MapValue){
            MapValue mv = (MapValue) v;
            t = mv.toString();
        }
        else
        {
            Utils.abort(super.getLine());
        }
        return new TextValue(t);
    }
    }

