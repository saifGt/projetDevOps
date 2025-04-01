package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.repositories.ICourseRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class CourseServicesImpl implements ICourseServices {

    private static final Logger logger = LogManager.getLogger(CourseServicesImpl.class);
    private final ICourseRepository courseRepository;

    @Override
    public List<Course> retrieveAllCourses() {
        logger.info("Récupération de tous les cours...");
        List<Course> courses = courseRepository.findAll();
        logger.debug("Nombre de cours récupérés : {}", courses.size());
        return courses;
    }

    @Override
    public Course addCourse(Course course) {
        logger.info("Ajout d'un nouveau cours : {}", course);
        Course savedCourse = courseRepository.save(course);
        logger.debug("Cours ajouté avec ID : {}", savedCourse.getNumCourse());
        return savedCourse;
    }

    @Override
    public Course updateCourse(Course course) {
        logger.info("Mise à jour du cours avec ID : {}", course.getNumCourse());
        Course updatedCourse = courseRepository.save(course);
        logger.debug("Cours mis à jour avec succès : {}", updatedCourse);
        return updatedCourse;
    }

    @Override
    public Course retrieveCourse(Long numCourse) {
        logger.info("Récupération du cours avec ID : {}", numCourse);
        return courseRepository.findById(numCourse).orElseGet(() -> {
            logger.error("Cours avec ID {} non trouvé", numCourse);
            return null;
        });
    }
}
