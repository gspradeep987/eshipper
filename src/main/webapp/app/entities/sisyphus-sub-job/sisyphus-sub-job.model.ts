import { ISisyphusJob } from 'app/entities/sisyphus-job/sisyphus-job.model';

export interface ISisyphusSubJob {
  id?: number;
  sUBJOBID?: number | null;
  rUNORDER?: string | null;
  sisyphusJob?: ISisyphusJob | null;
}

export class SisyphusSubJob implements ISisyphusSubJob {
  constructor(
    public id?: number,
    public sUBJOBID?: number | null,
    public rUNORDER?: string | null,
    public sisyphusJob?: ISisyphusJob | null
  ) {}
}

export function getSisyphusSubJobIdentifier(sisyphusSubJob: ISisyphusSubJob): number | undefined {
  return sisyphusSubJob.id;
}
