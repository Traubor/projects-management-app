import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges} from "@angular/core";
import {LabelWithId} from "./common-classes";

@Component({
    selector: 'simple-dropdown',
    template: `
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle form-control form-control-lg"
                    type="button" id="dropdownMenuButton"
                    data-bs-toggle="dropdown" aria-expanded="false">
                {{title}}
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <li *ngFor="let item of items">
                    <a class="dropdown-item"
                       [ngClass]="selectedItemId == item.id ? 'active' : ''"
                       (click)="onSelectionChange(item)">{{item.label}}</a>
                </li>
            </ul>
        </div>
    `
})
export class DropdownComponent implements OnChanges {

    @Input() items: LabelWithId[];
    @Input() selectedItemId: number;
    @Input() title: string = "";
    @Output() itemChange: EventEmitter<LabelWithId> = new EventEmitter();

    onSelectionChange(selectedItem: LabelWithId) {
        this.selectedItemId = selectedItem.id;
        this.itemChange.emit(selectedItem);
    }

    ngOnChanges(changes: SimpleChanges): void {
        // if (changes["items"]) {
        //     this.items.subscribe(loaded => {
        //         this.loadedItems = loaded;
        //     });
        // }
    }


}