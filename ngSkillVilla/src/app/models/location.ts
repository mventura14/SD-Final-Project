export class Location {
    id: number;
    address: string;
    city: string;
    state: string;
    zipcode: number;

    constructor(
        id = 0,
        address = '',
        city = '',
        state = '',
        zipcode = 0
    ) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }
}
