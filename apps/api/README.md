# Backend Scaffold

`apps/api` is organized as **a single Spring Boot deployment** with **Maven modules separated by responsibility**.

## Structure

```text
apps/api
├── pom.xml
├── platform-api
├── shared-kernel
├── identity
├── commute-profile
├── weather
├── traffic
├── incident-events
├── trip-history
├── tracking
└── recommendation
```

## Main Flow

1. `platform-api` exposes REST, security, observability, and documentation.
2. `recommendation` orchestrates the main use case.
3. `commute-profile` provides origin, destination, and buffers.
4. `weather`, `traffic`, and `incident-events` provide external signals.
5. `trip-history` provides the user baseline.
6. `tracking` is ready for the live session when a route starts.

## Design Rule

- `platform-api` knows every module.
- `recommendation` only consumes business modules and signal providers.
- `shared-kernel` only contains reusable value objects.
- No module depends on `platform-api`.
- If any module needs its own deployment in the future, it can be extracted without rebuilding the domain.

## Recommended Next Slice

1. Connect a real weather provider.
2. Connect a real routing and traffic provider.
3. Persist profiles and snapshots.
4. Harden JWT authentication once the identity provider is selected.
