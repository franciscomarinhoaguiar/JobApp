package com.faguiar.JobApp.job.impl;

import com.faguiar.JobApp.job.Job;
import com.faguiar.JobApp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private List<Job> jobs = new ArrayList<>();
    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        this.jobs.add(job);
    }

    @Override
    public Job getJobById(Long id) {
        for (Job job : jobs){
            if (job.getId().equals(id)){
                return job;
            }
        }
        return null;
    }

    @Override
    public boolean deleteJob(Long id) {
        Job job = getJobById(id);
        if (job != null){
            this.jobs.remove(job);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateJob(Long id, Job job) {
        Job jobFound = getJobById(id);
        if (jobFound != null){
            jobFound.setTitle(job.getTitle());
            jobFound.setDescription(job.getDescription());
            jobFound.setMinSalary(job.getMinSalary());
            jobFound.setMaxSalary(job.getMaxSalary());
            jobFound.setLocation(job.getLocation());

            return true;
        }
        return false;
    }
}
