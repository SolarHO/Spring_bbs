package kr.co.inhatcspring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.inhatcspring.beans.BoardPost;
import kr.co.inhatcspring.beans.UserInfo;
import kr.co.inhatcspring.mapper.BoardMapper;

@Controller
public class BoardController {
	
    // BoardMapper를 자동 주입하여 데이터베이스 작업을 처리
    @Autowired
    private BoardMapper boardMapper;
    
    // 루트 URL 요청을 처리하고 index.jsp 뷰를 반환
    @GetMapping("/")
    public String home() {
        return "index";
    }
    
    //login 페이지로 url요청 처리
    @GetMapping("/login")
    public String login() {
    	return "login";
    }
    
    //로그인 기능 컨트롤러
    @PostMapping("/loginProcess")
    public String loginProcess(@RequestParam("userid") String userid,
    		@RequestParam("password") String password, HttpSession session, Model model) {
    	UserInfo user = boardMapper.getUser(userid, password);
        if (user != null) {
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userid", user.getUserid());
            session.setAttribute("admin", user.getIsadmin());
            return "redirect:/";
        } else {
        	model.addAttribute("loginError", "ID나 비밀번호가 잘못되었습니다.");
            return "login";
        }
    }
    
    // 회원가입 페이지로 url요청 처리
    @GetMapping("/signup")
    public String signup(Model model) {
    	model.addAttribute("userForm", new UserInfo());
        return "signup";
    }
    
    // 회원가입 처리
    @PostMapping("/signup")
    public String signupProcess(@ModelAttribute("userForm") @Valid UserInfo user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        // 사용자 ID가 이미 존재하는지 확인
        int userCount = boardMapper.countUserById(user.getUserid());
        if (userCount > 0) {
            // 알림 메시지를 모델에 추가
            model.addAttribute("duplicateid", "이미 등록된 ID입니다.");
            // 회원가입 페이지로 리다이렉트
            return "signup";
        }
        // 사용자 등록
        boardMapper.insertUser(user);
        return "redirect:/login";
    }

    
    // 회원정보 수정 페이지로 url요청 처리
    @GetMapping("/updateUser")
    public String updateUser(Model model, HttpSession session) {
        String userid = (String) session.getAttribute("userid");
        UserInfo user = boardMapper.getUserById(userid);
        model.addAttribute("userForm", user);
        return "updateUser";
    }

    
    // 회원정보 수정 처리
    @PostMapping("/updateUserProcess")
    public String updateUserProcess(@ModelAttribute("userForm") @Valid UserInfo user, 
                                    BindingResult bindingResult, HttpSession session) {
    	if (bindingResult.hasErrors()) {
            return "updateUser";
        }
        session.setAttribute("username", user.getUsername());
        boardMapper.updateUser(user);
        return "redirect:/";
    }

    // 회원 탈퇴 처리
    @PostMapping("/deleteUser")
    public String deleteUser(HttpSession session) {
        String userid = (String) session.getAttribute("userid");
        boardMapper.deleteUser(userid);
        session.invalidate();
        return "redirect:/";
    }
    
    // 로그아웃 처리
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    
    // /list URL 요청을 처리하고 지정된 카테고리의 게시글 목록을 보여줌
    @GetMapping("/list")
    public String list(@RequestParam("category") String category, Model model, HttpSession session) {
        List<BoardPost> posts;
        if ("myPost".equals(category)) {
            // 세션에서 현재 로그인한 사용자의 ID를 가져옴
            String userid = (String) session.getAttribute("userid");
            // 현재 사용자가 작성한 게시글 목록을 가져옴
            posts = boardMapper.getPostsByUser(userid);
        } else {
            // 지정된 카테고리의 게시글 목록을 가져옴
            posts = boardMapper.getPosts(category);
        }
        // 모델에 게시글 목록과 카테고리 정보 추가
        model.addAttribute("posts", posts);
        model.addAttribute("category", category);
        // list.jsp 뷰를 반환
        return "list";
    }

    
    // /writ URL 요청을 처리하고 글 작성 페이지를 보여줌
    @GetMapping("/write")
    public String write(@RequestParam("category") String category, Model model) {
    	// 모델에 카테고리 정보를 추가
        model.addAttribute("category", category);
        // write.jsp 뷰를 반환
        return "write";
    }
   
    // /write URL로의 POST 요청을 처리하고 새로운 게시글을 DB에 저장
    @PostMapping("/write")
    public String writePost(BoardPost post, @RequestParam("category") String category, HttpSession session, Model model) {
        if(session.getAttribute("username") == null || "myPost".equals(category)) { //로그인이 안되어 있거나 내 게시글 카테고리로 글 작성을 할 경우
        	model.addAttribute("message", "권한이 없습니다.");
            return "write";
        }
        int admin = (int) session.getAttribute("admin");
        if (admin == 0 && (!"notice".equals(category) && !"faq".equals(category) && !"freeboard".equals(category))) { //권한 없이 커뮤니티 이외의 글을 작성할 경우
            model.addAttribute("message", "권한이 없습니다.");
            return "write";
        }
        post.setCategory(category);
        boardMapper.insertPost(post);
        return "redirect:/list?category=" + category;
    }

    
    // /view URL 요청을 처리하고 지정된 ID의 게시글을 보여줌
    @GetMapping("/view")
    public String view(@RequestParam("id") int id, @RequestParam("category") String category, Model model) {
        // 지정된 ID의 게시글을 가져옴
    	BoardPost post = boardMapper.getPost(id);
    	// 모델에 게시글과 카테고리 정보를 추가
        model.addAttribute("post", post);
        model.addAttribute("category", category);
        // view.jsp 뷰를 반환
        return "view";
    }
    // /update URL 요청을 처리하고 지정된 ID의 게시글을 수정하는 페이지를 보여줌
    @GetMapping("/update")
    public String update(@RequestParam("id") int id, @RequestParam("category") String category, Model model) {
    	// 지정된 ID의 게시글을 가져옴
        BoardPost post = boardMapper.getPost(id);
        // 모델에 게시글과 카테고리 정보를 추가
        model.addAttribute("post", post);
        model.addAttribute("category", category);
        // update.jsp뷰를 반환
        return "update";
    }
    
    
    // /update URL로의 POST 요청을 처리하고 게시글을 업데이트
    @PostMapping("/update")
    public String updatePost(BoardPost post, @RequestParam("category") String category) {
    	// 게시글의 카테고리 설정
        post.setCategory(category);
        // 게시글을 데이터베이스에서 업데이트
        boardMapper.updatePost(post);
        // 해당 카테고리의 게시글 목록 페이지로 리다이렉트
        return "redirect:/list?category=" + category;
    }
    // /delete URL로의 POST 요청을 처리하고 지정된 ID의 게시글을 삭제
    @PostMapping("/delete")
    public String deletePost(@RequestParam("id") int id, @RequestParam("category") String category) {
    	// 지정된 ID의 게시글을 DB에서 삭제
        boardMapper.deletePost(id);
        // 해당 카테고리의 게시글 목록 페이지로 리다이렉트(다른 URL로 요청을 다시 보내도록)
        return "redirect:/list?category=" + category;
    }
}
