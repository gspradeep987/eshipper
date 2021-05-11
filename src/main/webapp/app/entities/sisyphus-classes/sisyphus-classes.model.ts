import { ISisyphusSubJob } from 'app/entities/sisyphus-sub-job/sisyphus-sub-job.model';

export interface ISisyphusClasses {
  id?: number;
  name?: string | null;
  description?: string | null;
  sisyphusClasses?: ISisyphusSubJob | null;
}

export class SisyphusClasses implements ISisyphusClasses {
  constructor(
    public id?: number,
    public name?: string | null,
    public description?: string | null,
    public sisyphusClasses?: ISisyphusSubJob | null
  ) {}
}

export function getSisyphusClassesIdentifier(sisyphusClasses: ISisyphusClasses): number | undefined {
  return sisyphusClasses.id;
}
