package kh.spring.aspect;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.beans.factory.annotation.Autowired;

import kh.spring.dto.MemberDTO;

public class LogAdvisor {
	@Autowired
	private HttpSession session;
	// advice
	public void aopTest(JoinPoint jp) {
		// before, after는 JoinPoint 사용
		Signature sign = jp.getSignature();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		String currentTime = sdf.format(System.currentTimeMillis());
		Object[] args = jp.getArgs();
		HttpServletRequest req = (HttpServletRequest)args[0];
		req.setAttribute("request", "Hello AOP");
		System.out.println("==================================");
		System.out.println(currentTime);
		System.out.println(jp.getTarget()); // Advice가 적용되어 실행되는 메서드의 클래스
		System.out.println(sign.getName());
		System.out.println(session.getAttribute("loginId"));
		System.out.println("==================================");
	}
	
	
	
	public Object aroundTest(ProceedingJoinPoint pjp) throws Throwable {
	      // ProceedingJoinPoint는 around만 가질 수 있는 변수이다.
	      //before의 위치
	      long startTime = System.currentTimeMillis();
	      Object returnValue = (Object) pjp.proceed(); //PointCut 메서드 콜하기
	      //after의 위치
	      long endTime = System.currentTimeMillis();

	      System.out.print("작업처리시간 : ");
	      System.out.println(endTime - startTime);

	      // startTime = PointCut before, proceed = pointcut(home) 실행시점, endTime = PointCut after
		
	      return returnValue;

	      //Dispatcher로부터 Pointcut 메서드로 들어가다가 Controllor로 가는 길에 aroundTest가 낚아챈다.
	      //ProceedingJoinPoint는 JoinPoint를 상속받음
	      //proceed가 PointCut 메서드의 실행여부, 이를 실행하고 나온 다음 코드의 흐름이 다시 proceed 다음 줄로 돌아온다.
	      //(Controller에서 return jsp로 가는 게 아니라, proceed의 return으로 돌아옴)
	      //proceed 를 통해서 게시판의 기능을 콜해서 작업 수행 후 리런 값을 받는다.
	      //그 후 proceed 밑에 더 수행할 작업이 있으면 수행하고 
	   }
	
	public String aroundAdvice(ProceedingJoinPoint pjp) throws Throwable{
		try {
			MemberDTO dto = (MemberDTO) session.getAttribute("loginInfo");
			String id = dto.getId();
			 return (String)pjp.proceed();
			}catch(Exception e) {
				return "redirect:/error/loginError";
			}
		
	
	}


}
