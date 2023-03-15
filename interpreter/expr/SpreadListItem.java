//Revisado (igual ao single)
package interpreter.expr;

import java.util.List;
import interpreter.value.Value;

public class SpreadListItem extends ListItem {
    
    private Expr expr;

    public SpreadListItem(int line, Expr expr) {
        super(line);
        this.expr = expr;
    }

    @Override
    public List<Value<?>> items(){
        Value<?> elements = expr.expr();
        return List.of(elements);
    }
}
