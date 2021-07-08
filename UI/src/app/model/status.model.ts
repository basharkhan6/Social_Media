import {Location} from "./location.model";
import {User} from "./user.model";
import {PrivacyEnum} from "./enumeration/privacy-enum";

export class Status {

  id: number;
  details: string;
  privacy: PrivacyEnum;
  location: Location;
  user: User;
  createdAt: Date;
  updatedAt: Date;

}
