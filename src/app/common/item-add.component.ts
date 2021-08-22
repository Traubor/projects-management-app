import {ChangeDetectionStrategy, Component} from "@angular/core";
import {IHeaderParams} from "ag-grid-community";
import {IHeaderAngularComp} from "ag-grid-angular";

@Component({
    changeDetection: ChangeDetectionStrategy.OnPush,
    template: `
        <div class="addButton">
            <i class="bi bi-plus-circle" (click)="addItem()"></i>
        </div>
    `
})
export class ItemAddComponent implements IHeaderAngularComp  {

    private addFunction: () => void;

    agInit(params: IHeaderParams) {
        this.addFunction =  params["addFunction"];
    }

    addItem(){
        this.addFunction();
    }

    refresh(params: IHeaderParams): boolean {
        return false;
    }
}