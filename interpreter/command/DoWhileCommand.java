//Revisado
package interpreter.command;

import interpreter.expr.Expr;
import interpreter.util.Utils;
import interpreter.value.BoolValue;
import interpreter.value.Value;

public class DoWhileCommand extends Command {

    private Expr expr;
    private Command cmds;

    public DoWhileCommand(int line, Command cmds, Expr expr) {
        super(line);
        this.expr = expr;
        this.cmds = cmds;
    }

    @Override
    public void execute() {
        while (true) {
            cmds.execute();//Para fazer dowhile basta incluir isso?

            Value<?> v = expr.expr();
            if (!(v instanceof BoolValue))
                Utils.abort(super.getLine());
            
            BoolValue bv = (BoolValue) v;
            boolean b = bv.value();

            if (!b)
                break;
        }
    }
    
}
