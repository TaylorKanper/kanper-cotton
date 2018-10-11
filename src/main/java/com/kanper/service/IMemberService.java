package com.kanper.service;

import com.kanper.bean.MemberBean;
import com.kanper.bean.SoldGoodBean;
import com.kanper.common.Response;

import java.util.List;

public interface IMemberService {
    /**
     * 获取所有会员
     *
     * @return
     */
    List<MemberBean> getAllMembers();

    /**
     * 添加会员
     *
     * @param memberBean
     * @return
     */
    Response<String> addMember(MemberBean memberBean);

    /**
     * 删除会员
     *
     * @param memberId
     * @return
     */
    Response<String> delMember(Long memberId);

    /**
     * 修改会员
     *
     * @param memberBean
     * @return
     */
    Response<String> updateMember(MemberBean memberBean);

    /**
     * 查询会员购买的所有商品
     *
     * @param memberId 会员ID
     * @return
     */
    List<SoldGoodBean> queryAllSoldGoodsByMemberId(Long memberId);
}
