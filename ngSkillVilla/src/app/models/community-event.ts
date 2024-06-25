export class CommunityEvent {
 
    id: number
    community_id: number;
    description: string
    enabled: boolean
    // locationId: number
    // createdAt: string
    // UpdatedAt: string
    constructor(
        id = 0,
        community_id = 0,
        description = '',
        enabled = false,
        // locationId = 0,
        // createdAt = '',
        // UpdatedAt = ''
    ) {
        this.id = id;
        this.community_id = community_id;
        this.description = description;
        this.enabled = enabled;
    
}
}
