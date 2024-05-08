package com.study.mcdonaldskiosk.controller.admin;

import com.study.mcdonaldskiosk.domain.member.Member;
import com.study.mcdonaldskiosk.domain.menu.Menu;
import com.study.mcdonaldskiosk.domain.order.Order;
import com.study.mcdonaldskiosk.service.admin.AdminService;
import com.study.mcdonaldskiosk.service.member.MemberService;
import com.study.mcdonaldskiosk.service.menu.MenuService;
import com.study.mcdonaldskiosk.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin-management")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private OrderService orderService;

    // 관리자로 로그인 하면 관리자 페이지로 넘어가주기
    @GetMapping("/members")
    public String manageMembers(Model model) {
        List<Member> members = memberService.getAllMembers();
        model.addAttribute("members", members);
        return "admin/member";
    }

    @GetMapping("/members/{idx}/edit")
    public String editMemberForm(@PathVariable int idx, Model model) {
        Optional<Member> findedMember = memberService.findTopByIdx(idx);
        if (findedMember.isPresent()) {
            model.addAttribute("member", findedMember.get());
            return "admin/member_edit";
        } else {
            model.addAttribute("error", "No member found with id: " + idx);
            return "admin/member";
        }
    }

    // Form Action 방법
//    @PostMapping("/members/{idx}")
//    public String editMemberInfo(@PathVariable Integer idx, @ModelAttribute Member memberDetails) {
//        memberService.updateMemberInfo(idx, memberDetails);
//        return "redirect:/admin-management/members";
//    }
    // RestFul API 방법
    @PutMapping("/api/members/{idx}")
    public ResponseEntity<Member> updateMember(@PathVariable int idx, @RequestBody Member memberDetails) {
        Member updatedMember = memberService.updateMember(idx, memberDetails);
        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/members/{idx}")
    public ResponseEntity<?> deleteMember(@PathVariable int idx) {
        try {
            memberService.deleteMember(idx);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("유저 정보 찾기 실패 " + idx);
        }
    }

    @GetMapping("/menus")
    public String manageMenus(Model model) {
        List<Menu> menus = menuService.getAllMenus();
        model.addAttribute("menus", menus);
        return "admin/menu";
    }

    @GetMapping("/menus/{idx}/edit")
    public String editMenuForm(@PathVariable Long    idx, Model model) {
        Optional<Menu> editedMenu = menuService.findTopByIdx(idx);
        if (editedMenu.isPresent()) {
            model.addAttribute("menu", editedMenu.get());
            return "admin/menu_edit";
        } else {
            model.addAttribute("error", "No menu found with id: " + idx);
            return "admin/menu";
        }
    }

    @PutMapping("/api/menus/{idx}")
    public ResponseEntity<Menu> updateMenu(@PathVariable Long idx, @RequestBody Menu menuDetails) {
        Menu updatedMenu = menuService.updateMenuInfo(idx, menuDetails);
        return ResponseEntity.ok(updatedMenu);
    }

    @DeleteMapping("/menus/{idx}")
    public ResponseEntity<?> deleteMenu(@PathVariable Long idx) {
        try {
            menuService.deleteMenu(idx);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("메뉴 정보 찾기 실패 " + idx);
        }
    }

    @GetMapping("/orders")
    public String manageOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "admin/order";
    }

    @GetMapping("/orders/{idx}/edit")
    public String editOrderForm(@PathVariable int idx, Model model) {
        Optional<Order> editedOrder = orderService.findTopByIdx(idx);
        if (editedOrder.isPresent()) {
            model.addAttribute("order", editedOrder.get());
            return "admin/order_edit";
        } else {
            model.addAttribute("error", "No order found with id: " + idx);
            return "admin/order";
        }
    }

    @PutMapping("/api/orders/{idx}")
    public ResponseEntity<Order> updateOrder(@PathVariable int idx, @RequestBody Order orderDetails) {
        Order updatedOrder = orderService.updateOrderInfo(idx, orderDetails);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/orders/{idx}")
    public ResponseEntity<?> deleteOrder(@PathVariable int idx) {
        try {
            orderService.deleteOrder(idx);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("주문 정보 찾기 실패 " + idx);
        }
    }
}

