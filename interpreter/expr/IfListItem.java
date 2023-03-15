//Revisado
package interpreter.expr;

import java.util.List;

import interpreter.util.Utils;
import interpreter.value.BoolValue;
import interpreter.value.Value;

public class IfListItem extends ListItem {
    Expr expr;
    ListItem thenItem;
    ListItem elseItem;

    public IfListItem(int line, Expr expr, ListItem thenItem, ListItem elseItem){
        super(line);
        this.expr = expr;
        this.thenItem = thenItem;
        this.elseItem = elseItem;
    }
    // if '(' <expr> ')' <l-elem> [ else <l-elem> ]
    @Override
    public List<Value<?>> items(){
        Value<?> v = expr.expr();
        if (!(v instanceof BoolValue))
            Utils.abort(super.getLine());

        BoolValue bv = (BoolValue) v;
        
        List<Value<?>> lv;

        if(bv.value()){
            lv = thenItem.items();
        }
        else{ 
            lv = thenItem.items();
        }
        return lv;
    }

    }


