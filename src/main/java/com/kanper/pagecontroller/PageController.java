package com.kanper.pagecontroller;

import com.kanper.bean.UserBean;
import com.kanper.service.IGoodsService;
import com.kanper.service.IMenuService;
import com.kanper.service.ISoldGoodsService;
import com.kanper.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
@Slf4j
public class PageController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private ISoldGoodsService soldGoodsService;
    @Autowired
    private IGoodsService goodsService;

    /**
     * 登陆页面
     *
     * @return
     */
    @RequestMapping("/")
    public String login() {
        return "login";
    }

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("/user/login")
    public String home(String userName, String passWord, ModelMap modelMap, HttpSession session) {
        UserBean user = userService.login(userName, passWord);
        if (user != null) {
            session.setAttribute("user", user);
            session.setAttribute("menus", menuService.queryMenuByRole(user.isAdmin()));
            modelMap.addAttribute("user", user);
            modelMap.addAttribute("soldToday", soldGoodsService.queryTodaySoldGoods());
            return "home";
        }
        modelMap.addAttribute("loginfail", "登陆失败");
        return "login";
    }

    @RequestMapping("/home")
    public String home(ModelMap modelMap) {
        modelMap.addAttribute("soldToday", soldGoodsService.queryTodaySoldGoods());
        return "home";
    }

    /**
     * 商品购买管理页面
     *
     * @return
     */
    @RequestMapping("/product")
    public String product() {
        return "product";
    }

    /**
     * 商品后台管理页面
     *
     * @return
     */
    @RequestMapping("/backend")
    public String backend() {
        return "backend";
    }

    /**
     * 会员管理页面
     *
     * @return
     */
    @RequestMapping("/member")
    public String member() {
        return "member";
    }

    /**
     * 售后管理页面
     *
     * @return
     */
    @RequestMapping("/afterSale")
    public String afterSale() {
        return "afterSale";
    }

    /**
     * 退出
     *
     * @param session
     * @return
     */
    @RequestMapping("/exit")
    public String exit(HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("menus");
        return "login";
    }
}
