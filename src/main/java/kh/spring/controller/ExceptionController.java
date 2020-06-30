package kh.spring.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController { 
	// 예외를 컨트롤 하는 컨트롤러 : 모든 컨트롤러에서 발생하는 예외를 처리 해준다.
	@ExceptionHandler
	public String exceptionHandler(Exception e) { //일괄적 예외처리 : ExceptionHandler를 통해서 클래스 내의 모든 메서드의 예외를 받을 수 있다.(다른 메서드에는 모두 throws Exception을 붙여줌)
		e.printStackTrace();
		System.out.println("Exception Handler : 에러가 발생했습니다.");
		return "error";
	}
	
	@ExceptionHandler
	public String exceptionHandler(NumberFormatException nfe) { //일괄적 예외처리 : ExceptionHandler를 통해서 클래스 내의 모든 메서드의 예외를 받을 수 있다.(다른 메서드에는 모두 throws Exception을 붙여줌)
		System.out.println("NFException Handler : 에러가 발생했습니다.");
		return "error";
	}
	
	@ExceptionHandler
	public String exceptionHandler(NullPointerException npe) { //일괄적 예외처리 : ExceptionHandler를 통해서 클래스 내의 모든 메서드의 예외를 받을 수 있다.(다른 메서드에는 모두 throws Exception을 붙여줌)
		System.out.println("NullPointException Handler : 에러가 발생했습니다.");
		return "error";
	}
}
