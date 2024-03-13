package com.study.mvc.controller;

import com.study.mvc.aop.annotation.TimeAspect;
import com.study.mvc.dto.DBStudyReqDto;
import com.study.mvc.service.DBStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class DBController {

    @Autowired
    private DBStudyService dbStudyService;

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody DBStudyReqDto dbStudyReqDto) {//RequestBody json 데이터를 받을 때 사용

        System.out.println(dbStudyReqDto);
        return ResponseEntity.ok(dbStudyService.createStudy(dbStudyReqDto));
    }

    @TimeAspect
    @GetMapping("/select/study/{id}")
    public ResponseEntity<?> selectStudy(@PathVariable int id) {

        return ResponseEntity.ok(dbStudyService.findStudyById(id));
    }
    @GetMapping("/select/study")
    public ResponseEntity<?> selectStudy(@RequestParam String name) {

        return ResponseEntity.ok(dbStudyService.findStudyByName(name));
    }
    @TimeAspect
    @GetMapping("/select/studies")
    public ResponseEntity<?> selectStudyAll() {
        return ResponseEntity.ok(dbStudyService.findAll());
    }

    @DeleteMapping("/delete/study/{id}")
    public ResponseEntity<?> deleteStudy(@PathVariable int id) {
        return ResponseEntity.ok(dbStudyService.deleteById(id));
    }

    // update 어노테이션은 2개가 있다. 사용법도 동일 단, 표기법 상 차이 일뿐 업데이트를 하는 로직 자체는 직접 작성해야한다.
    // 즉, 기능적인 요소는 둘이 똑같은 요청이기에 작성 코드로 차이를 둔다.

    //    전체 수정 -> null 값을 보내면 빈 값을 업데이트 한다. 모든 값을 업데이트 한다.
    @PutMapping("/update/study/{id}")
    public ResponseEntity<?> putStudy(
            @PathVariable int id,
            @RequestBody DBStudyReqDto dbStudyReqDto) {
        return ResponseEntity.ok(dbStudyService.putById(id, dbStudyReqDto));
    }

    //    부분 수정 ->  null 값을 보내면 빈 값은 업데이트 하지 않고, 바뀐 부분만 업데이트 한다
    @PatchMapping("/update/study/{id}")
    public ResponseEntity<?> patchStudy(
            @PathVariable int id,
            @RequestBody DBStudyReqDto dbStudyReqDto) {
        return ResponseEntity.ok(dbStudyService.patchById(id, dbStudyReqDto));
    }
}

