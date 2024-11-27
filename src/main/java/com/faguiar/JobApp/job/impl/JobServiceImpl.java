package com.faguiar.JobApp.job.impl;

import com.faguiar.JobApp.job.Job;
import com.faguiar.JobApp.job.JobRepository;
import com.faguiar.JobApp.job.JobService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
   // private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
       return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJob(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean updateJob(Long id, Job job) {
        Optional<Job> jobOptional = jobRepository.findById(id);

        if (jobOptional.isPresent()) {
            Job jobFound = jobOptional.get();
            jobFound.setTitle(job.getTitle());
            jobFound.setDescription(job.getDescription());
            jobFound.setMinSalary(job.getMinSalary());
            jobFound.setMaxSalary(job.getMaxSalary());
            jobFound.setLocation(job.getLocation());
            jobRepository.save(jobFound);

            return true;
        }
        return false;
    }
}
