package me.nbarudi.salem.roles.Town.Random;

import me.nbarudi.salem.Role;
import me.nbarudi.salem.roles.Town.*;

import java.util.ArrayList;
import java.util.List;

public class RandomTown extends Role {

    public RandomTown(String name) {super(name);}

    @Override
    public List<Role> getRoles() {

        List<Role> roles = new ArrayList<Role>();
        roles.add(new Bodyguard("Bodyguard"));
        roles.add(new Doctor("Doctor"));
        roles.add(new Escort("Escort"));
        roles.add(new Investigator("Investigator"));
        roles.add(new Jailor("Jailor"));
        roles.add(new Lookout("Lookout"));
        roles.add(new Mayor("Mayor"));
        roles.add(new Medium("Medium"));
        roles.add(new Retributionist("Retributionist"));
        roles.add(new Sheriff("Sheriff"));
        roles.add(new Spy("Spy"));
        roles.add(new Transporter("Transporter"));
        roles.add(new Veteran("Veteran"));
        roles.add(new Vigilante("Vigilante"));

        return roles;
    }
}
