import {ChangeDetectionStrategy, Component} from "@angular/core";
import {IHeaderParams} from "ag-grid-community";
import {IHeaderAngularComp} from "ag-grid-angular";

@Component({
    changeDetection: ChangeDetectionStrategy.OnPush,
    template: `
        <div class="addButton">
            <i class="bi bi-plus-circle"
               data-bs-toggle="modal"
               [attr.data-bs-target]="addModalName"
            ></i>
        </div>
    `
})
export class ItemAddComponent implements IHeaderAngularComp {

    addModalName: string;

    agInit(params: IHeaderParams) {
        this.addModalName = "#" + params["addModalName"];
    }

    refresh(params: IHeaderParams): boolean {
        return true;
    }
}