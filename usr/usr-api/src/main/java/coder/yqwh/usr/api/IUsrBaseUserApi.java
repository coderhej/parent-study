package coder.yqwh.usr.api;

import coder.yqwh.usr.api.model.UsrBaseUserDTO;
import coder.yqwh.usr.util.ResponseEntityVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Administrator
 * @time 2020/12/2 21:24
 */
public interface IUsrBaseUserApi {

    /**
     * 保存签约配置
     * @param params
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    ResponseEntityVO<String> save(UsrBaseUserDTO params);
}
