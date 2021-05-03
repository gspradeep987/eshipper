import { ISisyphusSubJob } from 'app/entities/sisyphus-sub-job/sisyphus-sub-job.model';

export interface ISisyphusClasses {
  id?: number;
  iD?: number | null;
  nAME?: string | null;
  dESCRIPTION?: string | null;
  classes?: ISisyphusSubJob | null;
}

export class SisyphusClasses implements ISisyphusClasses {
  constructor(
    public id?: number,
    public iD?: number | null,
    public nAME?: string | null,
    public dESCRIPTION?: string | null,
    public classes?: ISisyphusSubJob | null
  ) {}
}

export function getSisyphusClassesIdentifier(sisyphusClasses: ISisyphusClasses): number | undefined {
  return sisyphusClasses.id;
}
