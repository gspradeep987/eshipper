import { ISisyphusJob } from 'app/entities/sisyphus-job/sisyphus-job.model';

export interface ISisyphusJobType {
  id?: number;
  name?: string | null;
  description?: string | null;
  sisyphusJobType?: ISisyphusJob | null;
}

export class SisyphusJobType implements ISisyphusJobType {
  constructor(
    public id?: number,
    public name?: string | null,
    public description?: string | null,
    public sisyphusJobType?: ISisyphusJob | null
  ) {}
}

export function getSisyphusJobTypeIdentifier(sisyphusJobType: ISisyphusJobType): number | undefined {
  return sisyphusJobType.id;
}
