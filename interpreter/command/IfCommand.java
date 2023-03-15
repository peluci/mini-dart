//Revisado
package interpreter.command;

import interpreter.expr.Expr;
import interpreter.util.Utils;
import interpreter.value.BoolValue;
import interpreter.value.Value;;

public class IfCommand extends Command{

    private Expr expr;
    private Command thenCmds;
    private  Command elseCmds;
    
    public IfCommand(int line, Expr expr, Command thenCmds, Command elseCmds) {
        super(line);
        this.expr = expr;
        this.thenCmds = thenCmds;
        this.elseCmds = elseCmds;
    }
    @Override
    public void execute() {
        Value<?> v = expr.expr();
        if (!(v instanceof BoolValue))
            Utils.abort(super.getLine());

        BoolValue bv = (BoolValue) v;
        
        if(bv.value()){
            thenCmds.execute();
        }
        else{ 
            if (elseCmds != null)
                elseCmds.execute();
        }
    }
    
}




// public void execute() {
//     Value<?> v = expr.expr();
//     if(thenCmds)

//     if (elseCmds != null)
//         elseCmds.setValue(v);
//     else ()
// }



/*  public void execute() {
        Value<?> v = rhs.expr();
        if (lhs != null)
            lhs.setValue(v);
    }



    @Override
    public void execute() {
        cmds.execute();
        while (true) {
            Value<?> v = expr.expr();
            if (!(v instanceof BoolValue))
                Utils.abort(super.getLine());
            
            BoolValue bv = (BoolValue) v;
            boolean b = bv.value();

            if (!b)
                break;

            cmds.execute();
        }
    }*/