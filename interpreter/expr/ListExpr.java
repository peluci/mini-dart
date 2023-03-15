//Revisado
package interpreter.expr;

import java.util.ArrayList;
import java.util.List;
import interpreter.util.Utils;
import interpreter.value.ListValue;
import interpreter.value.Value;

public class ListExpr extends Expr {

    private List<ListItem> list;

    public ListExpr(int line) {
        super(line);
        list = new ArrayList<ListItem>();
    }

    public void addItem(ListItem item) {
        list.add(item);
    }

    @Override
    public Value<?> expr() {
        List<Value<?>> l = new ArrayList<Value<?>>();

        for (ListItem item : list) {
            //List<Value<?>> value = item.expr();
            List<Value<?>> value = item.items();
            if (value == null)
                Utils.abort(super.getLine());
            //l.add(value);// Revisar aqui pra ver se o add eh de value ou de expr
        }
        ListValue lv = new ListValue(l);
        return lv;
    }
}
