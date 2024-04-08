package com.qt.script;

import javax.script.*;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2024/04/08 上午9:53
 * @version: V1.0
 */
public class ScriptDemo {
    public static void main(String[] args) throws ScriptException {
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("javascript");
        String script = "a > b";

        CompiledScript compiledScript = ((Compilable) scriptEngine).compile(script);

        Bindings bindings = scriptEngine.createBindings();
        bindings.put("a","2");
        bindings.put("b","3");

        Object eval = compiledScript.eval(bindings);
        System.out.println(eval);


    }
}
