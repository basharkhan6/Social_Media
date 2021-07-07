import {Location} from "./location.model";
import {User} from "./user.model";

export class Status {

  id: number;
  details: string;
  location: Location;
  user: User;
  createdAt: Date;
  updatedAt: Date;

}
