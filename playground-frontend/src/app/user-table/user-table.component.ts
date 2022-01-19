import {Component, OnInit} from "@angular/core";
import {Observable} from "rxjs";
import {User} from "./user.types";
import {UserService} from "../user.service";

@Component({
  selector: "app-user-table",
  templateUrl: "./user-table.component.html",
  styleUrls: ["./user-table.component.css"],
})
export class UserTableComponent implements OnInit {

  users$: Observable<User[]>;

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.refreshUsers();
  }

  refreshUsers(): void {
    this.users$ = this.userService.getUsers();
  }

}
