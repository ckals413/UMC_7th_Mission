package umc.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring.Service.MemberService.MemberQueryService;
import umc.spring.Service.MissionService.MissionQueryService;
import umc.spring.Service.StoreService.StoreQueryService;
import umc.spring.domain.enums.MissionStatus;

@SpringBootApplication
@EnableJpaAuditing

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	public CommandLineRunner run(ApplicationContext context) {
//		return args -> {
//			StoreQueryService storeService = context.getBean(StoreQueryService.class);
//
//			// 파라미터 값 설정
//			String name = "요아정";
//			Float score = 4.0f;
//
//			// 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
//			System.out.println("Executing findStoresByNameAndScore with parameters:");
//			System.out.println("Name: " + name);
//			System.out.println("Score: " + score);
//
//			storeService.findStoresByNameAndScore(name, score)
//					.forEach(System.out::println);
//		};
//	}

//	@Bean
//	public CommandLineRunner run(MissionQueryService missionQueryService) {
//		return args -> {
//			// 테스트할 회원 ID와 상태를 설정합니다
//			Long memberId = 1L;
//			MissionStatus status = MissionStatus.CHALLENGING;
//
//			// findMissionsByMemberIdAndStatus 메서드를 호출하여 결과를 콘솔에 출력합니다
//			missionQueryService.findMissionsByMemberIdAndStatus(memberId, status)
//					.forEach(mission -> System.out.println(mission.toString()));
//		};
//	}
//
	@Bean
	public CommandLineRunner run(ApplicationContext context) {
		return args -> {
			MemberQueryService memberService = context.getBean(MemberQueryService.class);
			Long memberId = 1L; // 조회할 멤버 ID 설정

			// Member 조회 및 출력
			memberService.findMember(memberId).ifPresent(member -> System.out.println("Member: " + member));
		};
	}
}
