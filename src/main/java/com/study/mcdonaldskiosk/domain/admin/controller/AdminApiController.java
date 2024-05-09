package com.study.mcdonaldskiosk.domain.admin.controller;

import com.study.mcdonaldskiosk.domain.member.entity.Member;
import com.study.mcdonaldskiosk.domain.member.repository.MemberRepository;
import com.study.mcdonaldskiosk.domain.menu.entity.Menu;
import com.study.mcdonaldskiosk.domain.menu.repository.MenuRepository;
import com.study.mcdonaldskiosk.domain.order.entity.Order;
import com.study.mcdonaldskiosk.domain.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/api/v1/admin")
public class AdminApiController {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private OrderRepository orderRepository;

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


    @PutMapping("/orders/{idx}")
    public ResponseEntity<Order> editOrder(@PathVariable int idx, @RequestBody Order orderDetails) {
        Order editedOrder = orderRepository.findTopByIdx(idx).orElseThrow(() -> new RuntimeException("해당 주문이 존재하지 않습니다"));
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