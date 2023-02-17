package com.greedy.section02.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/* 단위 테스트(unit test) 
 * 한 가지 기능(함수)마다 일을 잘 수행하는지 확인하며 특정 모듈이 의도된 대로 정확히 작동하는지 검증하는 절차
 * 연관 컴포넌트가 개발되지 않더라도 기능별 개발이 완료 된 것을 증명할 수 있다.
 * */
/* Project의 properties -> java build path -> libraries -> add library -> JUnit */
public class CalculatorTests {
	
	private Calculator calc = null;
	
	/* @BeforeAll : 모든 @Test 메소드 실행 전에 한 번만 수행
	 * @BeforeEach : @Test 메소드 실행 전에 매번 수행
	 * */
	
	@BeforeEach
	public void setup() {
		System.out.println("calculator 인스턴스 생성");
		calc = new Calculator();
	}
	
	@Test
	public void testSumTwoNumber_4와_5를_전달하면_합계가_9가_계산되는지_확인() {
		System.out.println("2-1 테스트 동작");
		int result = calc.sumTwoNumber(4, 5);
		
		assertEquals(9, result);
	}
	
	@Test
	@Disabled	// 테스트 하고 싶지 않은 경우 무시하도록 작성	
	public void testSumTwoNumber_6과_7을_전달하면_합계가_13이_되는지_확인() {
		System.out.println("2-2 테스트 동작");
		int result = calc.sumTwoNumber(6, 7);
		
		assertEquals(13, result, 1);
	}
	
	
	/* assertArrayEquals(a,b) : 배열 a와 b가 일치함을 확인
	 * assertEquals(a,b) : 객체 a와 b의 값이 일치함을 확인
	 * assertSame(a,b) : 객체 a와 b가 같은 객체임을 확인
	 * assertTrue(a) : a가 참인지 확인
	 * assertNotNull(a) : a 객체가 null이 아님을 확인 
	 * 등등
	 * */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
