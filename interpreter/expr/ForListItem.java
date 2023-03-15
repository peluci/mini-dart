//Revisado, pode conter erros (grandes chances)
package interpreter.expr;

import java.util.ArrayList;
import java.util.List;

import interpreter.util.Utils;
import interpreter.value.ListValue;
import interpreter.value.Value;

public class ForListItem extends ListItem {
    
    private Variable var;
    private Expr expr;
    private ListItem item;

    public ForListItem (int line, Variable var, Expr expr, ListItem item){
        super(line);
        this.var = var;
        this.expr = expr;
        this.item = item;
    }

    //<l-for> ::= for '(' <name> in <expr> ')' <l-elem>
    //[for (x in l) x] (incluir	os	elementos	x	de	uma	lista	l).
    @Override
    public List<Value<?>> items(){
        Value<?> v = expr.expr();
        if (!(v instanceof ListValue))
                Utils.abort(super.getLine());

        ListValue lv = (ListValue) v;
        List<Value<?>> lv2 = new ArrayList<>();
        for(Value<?> value: lv.value()){
            var.setValue(value);
            lv2.addAll(item.items());
        }
        
        return lv2;
    }
}