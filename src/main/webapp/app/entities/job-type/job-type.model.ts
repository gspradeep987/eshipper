import { ISisyphusJob } from 'app/entities/sisyphus-job/sisyphus-job.model';

export interface IJobType {
  id?: number;
  iD?: number | null;
  nAME?: string | null;
  dESCRIPTION?: string | null;
  jobType?: ISisyphusJob | null;
}

export class JobType implements IJobType {
  constructor(
    public id?: number,
    public iD?: number | null,
    public nAME?: string | null,
    public dESCRIPTION?: string | null,
    public jobType?: ISisyphusJob | null
  ) {}
}

export function getJobTypeIdentifier(jobType: IJobType): number | undefined {
  return jobType.id;
}
