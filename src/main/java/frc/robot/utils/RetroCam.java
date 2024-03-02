package frc.robot.utils;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.targeting.TargetCorner;

import edu.wpi.first.math.geometry.Transform3d;

public class RetroCam {

    private PhotonCamera camera;
    private PhotonPipelineResult result;
    private PhotonTrackedTarget bestTarget;

    public RetroCam(String cameraName) {
        camera = new PhotonCamera(cameraName);
        update();
    }

    public void update() {
        this.result = camera.getLatestResult();
        this.bestTarget = result.getBestTarget();
    }

    public double getYaw() {
        return bestTarget.getYaw();
    }

    public double getPitch() {
        return bestTarget.getPitch();
    }

    public double getArea() {
        return bestTarget.getArea();
    }

    public double getSkew() {
        return bestTarget.getSkew();
    }

    public List<TargetCorner> getCorners() {
        return bestTarget.getDetectedCorners();
    }

    public Transform3d getCameraToTarget() {
        return bestTarget.getBestCameraToTarget();
    }
}