package com.kanper.controller;

import com.kanper.annotation.Authorization;
import com.kanper.bean.MemberBean;
import com.kanper.common.ActionResult;
import com.kanper.common.Response;
import com.kanper.service.IMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member")
@Slf4j
public class MemberController {
    @Autowired
    private IMemberService memberService;

    @GetMapping("/getAllMember")
    public List<MemberBean> getAllMember() {
        return memberService.getAllMembers();
    }

    @PostMapping("/addMember")
    public ActionResult addMember(MemberBean memberBean) {
        try {
            Response<String> response = memberService.addMember(memberBean);
            if (response.isOk()) {
                return ActionResult.success("会员" + memberBean.getMemberName() + "添加成功", response.getResult());
            }
            return ActionResult.fail(response.getErrorMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ActionResult.fail("数据库已经存在该记录，或者发生错误");
        }
    }

    @PostMapping("/delMember")
    @Authorization
    public ActionResult delMember(Long memberId) {
        try {
            Response<String> response = memberService.delMember(memberId);
            if (response.isOk()) {
                return ActionResult.success(response.getResult());
            }
            return ActionResult.fail(response.getErrorMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ActionResult.fail(e.getMessage());
        }

    }

    @PostMapping("/updateMember")
    @Authorization
    public ActionResult updateMember(MemberBean memberBean) {
        try {
            Response<String> response = memberService.updateMember(memberBean);
            if (response.isOk()) {
                return ActionResult.success("会员" + memberBean.getMemberName() + "修改成功", response.getResult());
            }
            return ActionResult.fail(response.getErrorMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ActionResult.fail("数据库已经存在该记录，或者发生错误");
        }
    }

}
