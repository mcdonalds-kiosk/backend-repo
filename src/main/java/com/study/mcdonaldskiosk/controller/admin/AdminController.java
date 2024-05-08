package com.study.mcdonaldskiosk.controller.admin;

import com.study.mcdonaldskiosk.domain.member.Member;
import com.study.mcdonaldskiosk.domain.member.MemberRepository;
import com.study.mcdonaldskiosk.domain.menu.Menu;
import com.study.mcdonaldskiosk.domain.menu.MenuRepository;
import com.study.mcdonaldskiosk.domain.order.Order;
import com.study.mcdonaldskiosk.domain.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/members")
    public String manageMembers(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "admin/member";
    }

    @GetMapping("/members/{idx}/edit")
    public String editMemberForm(@PathVariable int idx, Model model) {
        Optional<Member> findedMember = memberRepository.findTopByIdx(idx);;
        if (findedMember.isPresent()) {
            model.addAttribute("member", findedMember.get());
            return "admin/member_edit";
        } else {
            model.addAttribute("error", "해당 유저가 존재하지 않습니다");
            return "admin/member";
        }
    }

    @PutMapping("/members/{idx}")
    public ResponseEntity<Member> editMember(@PathVariable int idx, @RequestBody Member memberDetails) {
        Member editedMember = memberRepository.findTopByIdx(idx).orElseThrow(() -> new RuntimeException("해당 유저가 존재하지 않습니다"));
        editedMember.setId(memberDetails.getId());
        editedMember.setPw(memberDetails.getPw());
        editedMember.setName(memberDetails.getName());
        editedMember.setRole(memberDetails.getRole());
        editedMember.setJoinDate(LocalDateTime.now());
        memberRepository.save(editedMember);

        return ResponseEntity.ok(editedMember);
    }

    @DeleteMapping("/members/{idx}")
    public ResponseEntity<?> deleteMember(@PathVariable int idx) {
        try {
            if (memberRepository.existsById(idx)) {
                memberRepository.deleteById(idx);
            } else {
                throw new RuntimeException("해당 유저가 존재하지 않습니다");
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("유저 정보 찾기 실패 " + idx);
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

    @PutMapping("/menus/{idx}")
    public ResponseEntity<Menu> editMenu(@PathVariable Long idx, @RequestBody Menu menuDetails) {
        Menu editedMenu = menuRepository.findTopByIdx(idx).orElseThrow(() -> new RuntimeException("해당 메뉴가 존재하지 않습니다"));
        editedMenu.setName(menuDetails.getName());
        editedMenu.setImageUrl(menuDetails.getImageUrl());
        editedMenu.setPrice(menuDetails.getPrice());
        editedMenu.setCategoryIdx(menuDetails.getCategoryIdx());
        editedMenu.setUpdatedAt(LocalDateTime.now());
        menuRepository.save(editedMenu);

        return ResponseEntity.ok(editedMenu);
    }

    @DeleteMapping("/menus/{idx}")
    public ResponseEntity<?> deleteMenu(@PathVariable Long idx) {
        try {
            if (menuRepository.existsById(idx)) {
                menuRepository.deleteById(idx);
            } else {
                throw new RuntimeException("해당 메뉴가 존재하지 않습니다");
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("메뉴 정보 찾기 실패 " + idx);
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

    @PutMapping("/orders/{idx}")
    public ResponseEntity<Order> editOrder(@PathVariable int idx, @RequestBody Order orderDetails) {
        Order editedOrder = orderRepository.findTopByIdx(idx).orElseThrow(() -> new RuntimeException("해당 주문이 존재하지 않습니다"));
        editedOrder.setIdx(orderDetails.getIdx());
        editedOrder.setMenuCount(orderDetails.getMenuCount());
        editedOrder.setTotalPrice(orderDetails.getTotalPrice());
        editedOrder.setStatus(orderDetails.getStatus());
        editedOrder.setMemberIdx(orderDetails.getMemberIdx());
        editedOrder.setCreatedAt(LocalDateTime.now());
        orderRepository.save(editedOrder);

        return ResponseEntity.ok(editedOrder);
    }

    @DeleteMapping("/orders/{idx}")
    public ResponseEntity<?> deleteOrder(@PathVariable int idx) {
        try {
            if (orderRepository.existsById(idx)) {
                orderRepository.deleteById(idx);
            } else {
                throw new RuntimeException("해당 주문이 존재하지 않습니다");
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("주문 정보 찾기 실패 " + idx);
        }
    }
}

