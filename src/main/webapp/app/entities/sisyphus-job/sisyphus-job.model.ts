import { ISisyphusSubJob } from 'app/entities/sisyphus-sub-job/sisyphus-sub-job.model';
import { ISisyphusJobParam } from 'app/entities/sisyphus-job-param/sisyphus-job-param.model';

export interface ISisyphusJob {
  id?: number;
  name?: string | null;
  scheduleMinute?: string | null;
  scheduleHour?: string | null;
  scheduleDay?: string | null;
  scheduleMonth?: string | null;
  shouldrunYN?: string | null;
  retries?: number | null;
  moniterScheduleYN?: boolean | null;
  subJobs?: ISisyphusSubJob[] | null;
  params?: ISisyphusJobParam[] | null;
}

export class SisyphusJob implements ISisyphusJob {
  constructor(
    public id?: number,
    public name?: string | null,
    public scheduleMinute?: string | null,
    public scheduleHour?: string | null,
    public scheduleDay?: string | null,
    public scheduleMonth?: string | null,
    public shouldrunYN?: string | null,
    public retries?: number | null,
    public moniterScheduleYN?: boolean | null,
    public subJobs?: ISisyphusSubJob[] | null,
    public params?: ISisyphusJobParam[] | null
  ) {
    this.moniterScheduleYN = this.moniterScheduleYN ?? false;
  }
}

export function getSisyphusJobIdentifier(sisyphusJob: ISisyphusJob): number | undefined {
  return sisyphusJob.id;
}
