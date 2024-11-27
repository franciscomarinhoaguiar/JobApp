package com.faguiar.JobApp.job;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/jobs")
public class JobController {
   private JobService jobService;
   private Long nextId = 1L;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAll());

    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job = jobService.getJobById(id);
        if (job != null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){

        boolean operation = jobService.deleteJob(id);
        if (operation){
            return new ResponseEntity<>("Job removed successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job Not Found", HttpStatus.NOT_FOUND);

    }

    @PutMapping("/jobs/{id}") //duas formas de fazer
   // @RequestMapping(value = "/jobs/{id}", method= RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job job){
        boolean operation = jobService.updateJob(id, job);
        if (operation){
            return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job Not Found", HttpStatus.NOT_FOUND);
    }
}
