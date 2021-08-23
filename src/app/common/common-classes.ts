export interface LabelWithId {
    label: string;
    id: number
}

export enum Role {
    ADMINISTRATOR = "ADMINISTRATOR",
    PROJECT_MANAGER = "PROJECT_MANAGER",
    DEVELOPER = "DEVELOPER"
}