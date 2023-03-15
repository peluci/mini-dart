//Tem algum erro mínimo que seja
package interpreter.command;

import interpreter.expr.Expr;
import interpreter.util.Utils;
import interpreter.value.BoolValue;
import interpreter.value.TextValue;
import interpreter.value.Value;

public class AssertCommand extends Command {

    private Expr expr;
    private Expr msg;

    public AssertCommand(int line, Expr expr, Expr msg) {
        super(line);
        this.expr = expr;
        this.msg = msg;
    }

    // assert: verificar se a condição lógica é verdadeira (primeiro argumento),
    // caso contrário exibir a mensagem “assert: msg”, onde msg é “not true” ou o
    // conteúdo do segundo argumento se houver.
    @Override
    public void execute() {
        Value<?> v = expr.expr();
        Value<?> m = msg.expr();
        if (!(v instanceof BoolValue))
            Utils.abort(super.getLine());

        TextValue tv = null;
        BoolValue bv = (BoolValue) v;
        if  (m instanceof TextValue){
            tv = (TextValue) m;
        }
        

            if (bv.value()) {
                System.out.println("assert: "+ tv);
            } else {
                System.out.println("assert: not true");
            }
    }
}
