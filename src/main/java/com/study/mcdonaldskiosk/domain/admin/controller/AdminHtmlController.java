package com.study.mcdonaldskiosk.domain.admin.controller;

import com.study.mcdonaldskiosk.domain.member.MemberRole;
import com.study.mcdonaldskiosk.domain.member.entity.Member;
import com.study.mcdonaldskiosk.domain.member.repository.MemberRepository;
import com.study.mcdonaldskiosk.domain.menu.entity.Menu;
import com.study.mcdonaldskiosk.domain.menu.repository.MenuRepository;
import com.study.mcdonaldskiosk.domain.order.entity.Order;
import com.study.mcdonaldskiosk.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminHtmlController {
    private final MemberRepository memberRepository;
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;

    @GetMapping("/members")
    public String manageMembers(Model model) {
        List<Member> members = memberRepository.findByRoleNot(MemberRole.UNKNOWN);
        model.addAttribute("members", members);
        return "admin/member";
    }

    @GetMapping("/members/{idx}/edit")
    public String editMemberForm(@PathVariable int idx, Model model) {
        Optional<Member> findedMember = memberRepository.findTopByIdx(idx);
        if (findedMember.isPresent()) {
            model.addAttribute("member", findedMember.get());
            return "admin/member_edit";
        } else {
            model.addAttribute("error", "해당 유저가 존재하지 않습니다");
            return "admin/member";
        }
    }

    @GetMapping("/menus")
    public String manageMenus(Model model) {
        List<Menu> menus = menuRepository.findAll();
        model.addAttribute("menus", menus);
        return "admin/menu";
    }

    @GetMapping("/menus/{idx}/edit")
    public String editMenuForm(@PathVariable Long idx, Model model) {
        Optional<Menu> editedMenu = menuRepository.findTopByIdx(idx);
        if (editedMenu.isPresent()) {
            model.addAttribute("menu", editedMenu.get());
            return "admin/menu_edit";
        } else {
            model.addAttribute("error", "해당 메뉴가 존재하지 않습니다");
            return "admin/menu";
        }
    }

    @GetMapping("/orders")
    public String manageOrders(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "admin/order";
    }

    @GetMapping("/orders/{idx}/edit")
    public String editOrderForm(@PathVariable int idx, Model model) {
        Optional<Order> editedOrder = orderRepository.findTopByIdx(idx);
        if (editedOrder.isPresent()) {
            model.addAttribute("order", editedOrder.get());
            return "admin/order_edit";
        } else {
            model.addAttribute("error", "주문 정보 찾기 실패 " + idx);
            return "admin/order";
        }
    }
}