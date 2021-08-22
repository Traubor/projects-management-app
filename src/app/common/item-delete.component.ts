import {ChangeDetectionStrategy, Component} from "@angular/core";
import {ICellRendererParams} from "ag-grid-community";
import {ICellRendererAngularComp} from "ag-grid-angular";

@Component({
    changeDetection: ChangeDetectionStrategy.OnPush,
    template: `
        <i class="bi bi-trash" (click)="delete()"></i>
    `
})
export class ItemDeleteComponent implements ICellRendererAngularComp {

    private deletionFunction: (any) => void;
    private row: any;

    protected setInputs(params: ICellRendererParams): void {
        this.deletionFunction = params["deletionFunction"];
        this.row = params.data;
    }

    delete() {
        this.deletionFunction(this.row.id);
    }

    agInit(params: ICellRendererParams): void {
        this.setInputs(params);
    }

    refresh(params: ICellRendererParams): boolean {
        this.setInputs(params);
        return true;
    }
}