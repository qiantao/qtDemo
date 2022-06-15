package mybatisDemo.builder;

import demo.BuildEntityStr;
import mybatisDemo.entity.TableInfo;
import util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/09/17 10:52
 * @version: V1.0
 */
public class ControllerBuilder {
    private static String dateStr = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

    public static String buildController(TableInfo tableInfo,String module,String h3module){

        String tableName = tableInfo.getTableName();
        String controllerPath=tableInfo.getControllerPath();
        String managerPath = tableInfo.getManagerPath();
        String utilPath = tableInfo.getUtilPath();
        String voPath = tableInfo.getVoPath();
        String groupPath = tableInfo.getGroupPath();
        StringBuffer buf = new StringBuffer();
        String mName = StringUtil.isNullTrim(module)?h3module:module;

        buf.append("package ").append(controllerPath).append(";\n");
        buf.append("\n");
        buf.append("import com.hydee.h3.common.log.LoggerBuilder;\n");
        buf.append("import ").append(managerPath).append(".").append(BuildEntityStr.axaToAxA(mName)).append("Manager;\n");
        buf.append("import ").append(utilPath).append(".").append(BuildEntityStr.axaToAxA(h3module)).append("Util;\n");
        buf.append("import com.hydee.common.validate.ParameterValidate;\n");
        buf.append("import com.hydee.common.beans.message.enums.MessageEnum;\n");
        buf.append("import ").append(voPath).append(".").append(BuildEntityStr.axaToAxA(mName)).append("VO;\n");
        buf.append("import com.hydee.common.beans.message.MessageData;\n");
        buf.append("import ").append(groupPath).append(".Member1Group;\n");
        buf.append("import com.hydee.h3.controller.BaseController;\n");
        buf.append("import org.slf4j.Logger;\n");
        buf.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        buf.append("import org.springframework.web.bind.annotation.*;\n");
        buf.append("\n");

        buf.append("/**\n" +
                " * @ClassName:  ").append(BuildEntityStr.axaToAxA(tableName)).append("Controller\n" +
                " * @Description: ").append(BuildEntityStr.axaToAxA(tableName)).append("Controller\n" +
                " * @author: QianTao\n" +
                " * @date:  ").append(dateStr).append("\n" +
                " * @version: V1.0\n" +
                " */\n");
        buf.append("@RestController\n");
        buf.append("@RequestMapping(value = \"public/").append(mName).append("/\")\n");
        buf.append("public class ").append(BuildEntityStr.axaToAxA(tableName)).append("Controller extends BaseController {\n");
        buf.append("    private Logger log = LoggerBuilder.getLogger(this.getClass());\n");
        buf.append("    @Autowired\n");
        buf.append("    private ").append(BuildEntityStr.axaToAxA(mName)).append("Manager ").append(BuildEntityStr.axaToAxA(mName)).append("Manager;\n\n");

        buf.append("     /**\n" +
                "     * 添加\n" +
                "     * @param ").append(mName).append("VO\n" +
                "     * @return\n" +
                "     */\n" +
                "    @PostMapping(value = \"add\")\n" +
                "    public MessageData add(@RequestBody ").append(BuildEntityStr.axaToAxA(mName)).append("VO ").append(mName).append("VO){\n" +
                "        ").append(BuildEntityStr.axaToAxA(h3module)).append("Util.setSession(getCgCode(),getCpCode(),getBusiCode(),getUserId());\n" +
                "        //校验入参\n" +
                "        ParameterValidate.ValidResult validResult = ParameterValidate.validateBean(").append(mName).append("VO, ").append(BuildEntityStr.axaToAxA(mName)).append("Group.ADD").append(mName.toUpperCase()).append(".class);\n" +
                "        if (validResult.hasErrors()) {\n" +
                "            return MessageData.fail(MessageEnum.BIZ_PARAMETER_ERROR.getCode(), validResult.getErrors());\n" +
                "        }\n" +
                "\n" +
                "        return ").append(BuildEntityStr.axaToAxA(mName)).append("Manager.add(").append(mName).append("VO);\n" +
                "    }\n\n");

        buf.append("     /**\n" +
                "     * 修改\n" +
                "     * @param ").append(mName).append("VO\n" +
                "     * @return\n" +
                "     */\n" +
                "    @PostMapping(value = \"mod\")\n" +
                "    public MessageData mod(@RequestBody ").append(BuildEntityStr.axaToAxA(mName)).append("VO ").append(mName).append("VO){\n" +
                "        ").append(BuildEntityStr.axaToAxA(h3module)).append("Util.setSession(getCgCode(),getCpCode(),getBusiCode(),getUserId());\n" +
                "        //校验入参\n" +
                "        ParameterValidate.ValidResult validResult = ParameterValidate.validateBean(").append(mName).append("VO, ").append(BuildEntityStr.axaToAxA(mName)).append("Group.MOD").append(mName.toUpperCase()).append(".class);\n" +
                "        if (validResult.hasErrors()) {\n" +
                "            return MessageData.fail(MessageEnum.BIZ_PARAMETER_ERROR.getCode(), validResult.getErrors());\n" +
                "        }\n" +
                "\n" +
                "        return ").append(BuildEntityStr.axaToAxA(mName)).append("Manager.add(").append(mName).append("VO);\n" +
                "    }\n\n");



        buf.append("     /**\n" +
                "     * 根据ID查询详情\n" +
                "     * @param id\n" +
                "     * @return\n" +
                "     */\n" +
                "    @GetMapping(value = \"detail\")\n" +
                "    public MessageData detail(@RequestParam(\"id\") Long id){\n" +
                "        ").append(BuildEntityStr.axaToAxA(h3module)).append("Util.setSession(getCgCode(),getCpCode(),getBusiCode(),getUserId());\n" +
                "        if (id ==null || id <1) {\n" +
                "            return MessageData.fail(MessageEnum.BIZ_PARAMETER_ERROR);\n" +
                "        }\n" +
                "\n" +
                "        return ").append(BuildEntityStr.axaToAxA(mName)).append("Manager.detail(id);\n" +
                "    }");

        buf.append("\n\n}");


        return buf.toString();
    }
}
